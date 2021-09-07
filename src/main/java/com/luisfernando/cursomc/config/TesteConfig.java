package com.luisfernando.cursomc.config;

import com.luisfernando.cursomc.services.DBService;
import com.luisfernando.cursomc.services.EmailServices;
import com.luisfernando.cursomc.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TesteConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean intantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailServices emailServices(){
        return new MockEmailService();
    }
}
