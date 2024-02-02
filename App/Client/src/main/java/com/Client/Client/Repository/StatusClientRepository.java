package com.Client.Client.Repository;

import com.Client.Client.OrderDocument.StatusLogClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusClientRepository extends MongoRepository<StatusLogClientEntity, String> {
}
