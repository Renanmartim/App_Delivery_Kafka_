package com.Client.Client.Repository;

import com.Client.Client.Model.ClientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<ClientModel,String> {
    Optional<ClientModel> findByCpf(Long cpf);
}
