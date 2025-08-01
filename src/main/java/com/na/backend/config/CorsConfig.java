package com.na.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200"); // Cambia "*" si solo permites localhost:4200
        config.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, etc.)
        config.addAllowedHeader("*"); // Permitir todos los encabezados
        config.setAllowCredentials(true); // Permitir cookies o autenticación

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplicar a todas las rutas

        return new CorsFilter(source);
    }

}
