package com.luisfernando.cursomc.config;

import com.luisfernando.cursomc.services.DBService;
import com.luisfernando.cursomc.services.EmailServices;
import com.luisfernando.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean intantiateDatabase() throws ParseException {

        if(!"create".equals(strategy)){
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailServices emailServices(){
        return new SmtpEmailService();
    }
}
