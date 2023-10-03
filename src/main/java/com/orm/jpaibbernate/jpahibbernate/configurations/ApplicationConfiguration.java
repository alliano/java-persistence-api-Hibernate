package com.orm.jpaibbernate.jpahibbernate.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

@Configuration
public class ApplicationConfiguration {
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BELAJAR-JPA");
        return entityManagerFactory;
    }

    @Bean(value = "faker")
    public Faker faker() {
        return new Faker();
    }
}
