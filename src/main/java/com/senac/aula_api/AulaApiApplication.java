package com.senac.aula_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AulaApiApplication extends SpringBootServletInitializer {

   public static void main(String[] args) {
       SpringApplication.run(AulaApiApplication.class, args);
   }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AulaApiApplication.class);
    }
}
