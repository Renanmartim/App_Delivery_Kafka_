package com.Client.Client.Config;

import com.Client.Client.Dto.UserLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.Client.Client.Repository.UserLoginRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    TokenService tokenService;

    UserLoginRepository userLoginRepository;

    public SecurityFilter(UserLoginRepository userLoginRepository,TokenService tokenService){
        this.tokenService=tokenService;
        this.userLoginRepository=userLoginRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if(token != null){

            var login = tokenService.validateToken(token);

            System.out.println(token);
            System.out.println(login);
            UserLogin user = userLoginRepository.findByEmail(login);

            if(user != null) {
                System.out.println(user);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
