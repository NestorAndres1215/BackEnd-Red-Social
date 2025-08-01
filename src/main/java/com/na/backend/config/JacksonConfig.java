package com.na.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.na.backend.security.serializer.GrantedAuthorityJacksonDeserializer;

@Configuration
public class JacksonConfig {
        @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(GrantedAuthority.class, new GrantedAuthorityJacksonDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
