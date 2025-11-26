package com.example.Novameuble.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    public class CorsConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200") // ton front
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders(
                            "Origin",
                            "Content-Type",
                            "Accept",
                            "Authorization",
                            "Cache-Control",
                            "X-Requested-With",
                            "Access-Control-Request-Method",
                            "Access-Control-Request-Headers"
                    )
                    .allowCredentials(false);
        }
    }

