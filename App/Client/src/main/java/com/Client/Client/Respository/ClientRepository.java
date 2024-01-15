package com.Client.Client.Respository;

import com.Client.Client.OrderDocument.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Entity, String> {
}
