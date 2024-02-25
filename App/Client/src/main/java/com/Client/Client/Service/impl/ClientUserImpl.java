package com.Client.Client.Service.impl;
import com.Client.Client.Config.TokenService;
import com.Client.Client.Dto.CardPayment;
import com.Client.Client.Dto.UserLogin;
import com.Client.Client.Exceptions.*;
import com.Client.Client.Model.ClientModel;
import com.Client.Client.Repository.UserLoginRepository;
import com.Client.Client.Response.ResponseCreate;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import com.Client.Client.Model.EntityModel;
import com.Client.Client.Service.ClientTemplate;
import com.Client.Client.Service.ClientUser;
import com.Client.Client.Repository.ClientRepository;
import com.Client.Client.Service.LogOrderService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.naming.NameNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ClientUserImpl implements ClientUser {

    private final ClientTemplate client;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserLoginRepository userLoginRepository;


    private final ClientRepository clientRepository;

    private ClientUserImpl (ClientRepository clientRepository, ClientTemplate client){
        this.clientRepository = clientRepository;

        this.client = client;
    }

    public static boolean validateCPF(long cpf) {

        String cpfString = String.valueOf(cpf);

        cpfString = cpfString.replaceAll("\\D", "");

        if (cpfString.length() != 11) {
            return false;
        }

        char firstDigit = cpfString.charAt(0);
        boolean allSame = cpfString.chars().allMatch(digit -> digit == firstDigit);
        if (allSame) {
            return false;
        }

        if (!validateDigit(cpfString, 9)) {
            return false;
        }

        if (!validateDigit(cpfString, 10)) {
            return false;
        }

        return true;
    }

    private static boolean validateDigit(String cpf, int position) {
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += (cpf.charAt(i) - '0') * (position + 1 - i);
        }
        int remainder = 11 - (sum % 11);
        if (remainder >= 10) {
            remainder = 0;
        }
        return (remainder == cpf.charAt(position) - '0');
    }
    @Override
    public ResponseEntity<ResponseCreate> register(ClientModel client) {

        if(clientRepository.findByEmailExist(client.getUserLoginModel().getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("The Email Already Exists In Database");
        }

        if(clientRepository.findByCpf(client.getCpf()).isPresent()){

            throw new CpfAlreadyExistsException("The Cpf Already Exists In Database");

        }

        var validateCpf = validateCPF(client.getCpf());

        if(!validateCPF(client.getCpf())){

            throw new CpfAlreadyFormatException("The Cpf is not real!");

        }

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://brasilaberto.com/api/v1/zipcode/" + client.getUserAdress().getCep();

        try {

            String jsonResponse = restTemplate.getForObject(url, String.class);

                var startClient = clientRepository.save(client);

                createUserLogin(client);

                var userResponse = new ResponseCreate(client.getName(), client.getUserLoginModel().getEmail());

                return ResponseEntity.ok().body(userResponse);

        } catch (HttpClientErrorException.NotFound ex) {

            String responseBody = ex.getResponseBodyAsString();

            throw new InvalidCepException("The Cep Is Incorrect! Try Again!");

        } catch (Exception ex) {

            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }

    }

    @Override
    public ResponseEntity<String> modify(String id, ClientModel client) {

        var startClient = clientRepository.findById(id);

        if(startClient.isPresent()){
            var clientObject = startClient.get();

            clientObject.setUserAdress(client.getUserAdress());
            clientObject.setCpf(client.getCpf());
            clientObject.setName(client.getName());
            clientObject.setUserLoginModel(client.getUserLoginModel());

            clientRepository.save(clientObject);

            return ResponseEntity.ok().body("Change Made Successfully!");
        }

        return ResponseEntity.badRequest().body("Id Not Exists!");
    }

    @Override
    public Boolean verifyClient(String id) {

        var startClient = clientRepository.findById(id);

        return startClient.isPresent();

    }

    @Override
    public String sendMensage(EntityModel entity) {

        var verifyId = this.verifyClient(entity.getId());

        if (verifyId) {

            RestTemplate restTemplate = new RestTemplate();

            Boolean url = restTemplate.getForObject("http://localhost:8580/v1/verify/" + entity.getName(), Boolean.class);

            if (Boolean.TRUE.equals(url)) {

                String payment = "http://localhost:8989/payment/pay";

                // Create your request body
                CardPayment requestBody = new CardPayment(entity.getCardPayment().getName(), entity.getCardPayment().getNumber(), entity.getCardPayment().getMaturity(), entity.getCardPayment().getCvv(), entity.getCardPayment().getValue());

                // Set headers if needed
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                // Add any other headers if needed

                // Create the HTTP entity with headers and body
                HttpEntity<CardPayment> requestEntity = new HttpEntity<>(requestBody, headers);

                // Send the request
                ResponseEntity<Boolean> responseEntity = restTemplate.exchange(payment, HttpMethod.POST, requestEntity, Boolean.class);

                String responseBody = String.valueOf(responseEntity.getBody());

                if(Boolean.parseBoolean(responseBody.trim()) == Boolean.TRUE) {

                    // Handle the response

                    String orderForTopic = entity.getId() + " | " + entity.getName() + " ; " + entity.getDescription() + " : Send To Kitchen ";
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = currentDateTime.format(formatter);
                    String clientmensage = entity.getId() + "|Send to Kitchen" + "(" + formattedDateTime + ")";
                    client.sendMensageKitchen(orderForTopic);
                    client.sendMensageClient(clientmensage);
                    return orderForTopic;
                }

                throw new PaymentException("Payment is not found!");

            }

            throw new NameDishNotFoundException("The name of the dish does not exist on the menu!");

        }

        throw new IdNotExistsException("Id Not Exists!");
    }

    public ResponseEntity createUserLogin(ClientModel client){

        if(this.userLoginRepository.findByEmail(client.getUserLoginModel().getPassword()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(client.getUserLoginModel().getPassword());
        UserLogin newUser = new UserLogin(client.getUserLoginModel().getEmail(), encryptedPassword);

        this.userLoginRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
