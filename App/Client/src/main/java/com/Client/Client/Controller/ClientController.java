package com.Client.Client.Controller;

import com.Client.Client.Config.TokenService;
import com.Client.Client.Dto.UserLogin;
import com.Client.Client.Repository.UserLoginRepository;
import com.Client.Client.Response.ResponseCreate;
import jakarta.validation.Valid;
import com.Client.Client.Exceptions.InvalidCepException;
import com.Client.Client.Model.ClientModel;
import com.Client.Client.Model.EntityModel;
import com.Client.Client.Service.ClientListener;
import com.Client.Client.Service.ClientTemplate;
import com.Client.Client.Service.ClientUser;
import com.Client.Client.Service.LogOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ClientController {


    private final LogOrderService logOrderService;

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    private final ClientUser clientUser;

    private ClientController(LogOrderService logOrderService, ClientUser clientUser){

        this.logOrderService = logOrderService;
        this.clientUser = clientUser;

    }

    @PostMapping
    public String send(@RequestBody EntityModel entity) {
        return clientUser.sendMensage(entity);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> status(@PathVariable String id) {
        return logOrderService.takeStatus(id);
    }

    @PostMapping("/user/create")
    public ResponseEntity<ResponseCreate> create(@RequestBody ClientModel client){

        return clientUser.register(client);
    }

    @PostMapping("/user/modify/{id}")
    public ResponseEntity<String> modify(@PathVariable String id, @RequestBody ClientModel client){
        return clientUser.modify(id,client);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserLogin data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        System.out.println("0"+usernamePassword);
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println("1"+ auth);

        var token = tokenService.generateToken((UserLogin) auth.getPrincipal());

        System.out.println("2"+token);

        return ResponseEntity.ok(token);
    }
    



}
