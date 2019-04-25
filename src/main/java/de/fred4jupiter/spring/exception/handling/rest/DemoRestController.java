package de.fred4jupiter.spring.exception.handling.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable("name") String name) {
        if (name.equals("Michael")) {
            throw new IllegalArgumentException("Name Michael is not allowed.");
        }

        return "Hello " + name;
    }
}
