package de.fred4jupiter.spring.exception.handling;

import de.fred4jupiter.spring.exception.handling.client.CustomRestTemplateResponseErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestOperations restOperations(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
//		return restTemplateBuilder.errorHandler(new CustomRestTemplateResponseErrorHandler()).build();
	}
}
