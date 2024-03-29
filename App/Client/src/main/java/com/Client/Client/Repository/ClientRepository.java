package com.Client.Client.Repository;

import com.Client.Client.Model.ClientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<ClientModel,String> {
    Optional<ClientModel> findByCpf(Long cpf);

    @Query("{ 'userLoginModel.email' : ?0 }")
    UserDetails findByEmail(String email);
    @Query("{ 'userLoginModel.email' : ?0 }")
    Optional<Object> findByEmailExist(String email);
}
