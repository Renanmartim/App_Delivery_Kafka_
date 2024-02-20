package com.Client.Client.Repository;

import com.Client.Client.Dto.UserLogin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends MongoRepository<UserLogin, String> {

    UserLogin findByEmail(String email);

}
