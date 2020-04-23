package com.plivo.contactbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@Configuration
@PropertySource("application.properties")
@EnableRedisRepositories
public class ContactBookApplication {


	private static final Logger LOGGER= LoggerFactory.getLogger(ContactBookApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ContactBookApplication.class, args);

		LOGGER.info("Getting started with spring boot application");



	}

}
