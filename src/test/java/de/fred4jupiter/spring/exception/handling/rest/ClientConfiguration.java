package de.fred4jupiter.spring.exception.handling.rest;

import feign.Contract;
import feign.Feign;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignClientsConfiguration.class)
public class ClientConfiguration {

    @Bean
    public CustomErrorDecoder customErrorDecoder() {
        return new CustomErrorDecoder();
    }

//    @Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }

    @Bean
    public DemoRestClient demoRestClient() {
        return Feign.builder().contract(new SpringMvcContract()).errorDecoder(customErrorDecoder()).target(DemoRestClient.class, "http://localhost:8080");
    }
}
