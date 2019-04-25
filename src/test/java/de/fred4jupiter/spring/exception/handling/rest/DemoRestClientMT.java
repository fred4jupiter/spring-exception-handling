package de.fred4jupiter.spring.exception.handling.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DemoRestClientMT {

    @Autowired
    private DemoRestClient demoRestClient;

    @Test
    public void callRestServiceWithFeign() {
        try {
            demoRestClient.greeting("Michael");
            fail("HttpServerErrorException should be thrown");
        } catch (HttpServerErrorException e) {
            assertNotNull(e);
            assertEquals("Name Michael is not allowed.", e.getStatusText());
        }
    }
}
