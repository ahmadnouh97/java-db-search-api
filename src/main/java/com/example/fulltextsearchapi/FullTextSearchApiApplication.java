package com.example.fulltextsearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class FullTextSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullTextSearchApiApplication.class, args);
	}

}
