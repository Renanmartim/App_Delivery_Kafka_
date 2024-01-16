package com.Client.Client.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ClientConfig {

    @Bean
    public ConcurrentHashMap<UUID, String> orderStatusMap() {
        return new ConcurrentHashMap<>();
    }
}
