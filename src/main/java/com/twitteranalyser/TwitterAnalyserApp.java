package com.twitteranalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class TwitterAnalyserApp {

    public static void main(String[] args) {
        SpringApplication.run(TwitterAnalyserApp.class, args);
    }

}