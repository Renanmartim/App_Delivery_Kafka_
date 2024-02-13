package com.Client.Client.Repository;

import com.Client.Client.Model.StatusLogClientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusClientRepository extends MongoRepository<StatusLogClientModel, String> {


}
