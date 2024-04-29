package com.github.beltraliny.desafionexfar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    public MongoTemplate mongoTemplate() {
        String uri = "mongodb://" +
                username + ":" +
                password + "@" +
                host + ":" +
                port + "/" +
                database + "?authSource=admin";

        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(uri));
    }
}
