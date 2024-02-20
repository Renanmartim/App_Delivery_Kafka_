package com.Client.Client.Service.impl;

import com.Client.Client.Repository.ClientRepository;
import com.Client.Client.Repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {


    UserLoginRepository userLoginRepository;

    private AuthorizationService (UserLoginRepository userLoginRepository){
        this.userLoginRepository = userLoginRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userLoginRepository.findByEmail(username);
    }
}
