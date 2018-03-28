package com.csipon.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class ErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}
}
