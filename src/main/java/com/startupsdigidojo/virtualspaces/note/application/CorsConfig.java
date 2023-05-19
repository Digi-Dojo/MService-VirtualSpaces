package com.startupsdigidojo.virtualspaces.note.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
class CorsConfig {

}
/*
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Value("${com.startupsdigidojo.virtualspaces.note.web.cors.origins}")
    private String corsOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins(corsOrigins);
    }
}
*/