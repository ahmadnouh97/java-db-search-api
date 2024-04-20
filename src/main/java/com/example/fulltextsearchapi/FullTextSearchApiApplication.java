package com.example.fulltextsearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication()
@EnableConfigurationProperties({SearchConfig.class})
@EntityScan("com.example.fulltextsearchapi.User.models")
@EnableJpaRepositories("com.example.fulltextsearchapi.User.repo")
public class FullTextSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullTextSearchApiApplication.class, args);
	}

}
