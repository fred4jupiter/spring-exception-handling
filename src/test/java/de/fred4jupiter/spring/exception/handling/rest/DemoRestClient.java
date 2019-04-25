package de.fred4jupiter.spring.exception.handling.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "demoRestClient", url = "http://localhost:8080")
public interface DemoRestClient {

    @GetMapping("/greeting/{name}")
    String greeting(@PathVariable("name") String name);
}
