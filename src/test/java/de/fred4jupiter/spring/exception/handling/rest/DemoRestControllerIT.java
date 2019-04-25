package de.fred4jupiter.spring.exception.handling.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoRestControllerIT {

    private static final Logger LOG = LoggerFactory.getLogger(DemoRestControllerIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    private RestOperations restOperations;

    @Test
    public void callMethodAndExpectCorrectErrorMessage() {
        try {
            restOperations.getForEntity("http://localhost:" + port + "/greeting/Michael", String.class);
            fail("HttpClientErrorException should be thrown");
        } catch (HttpClientErrorException e) {
            assertNotNull(e);
            String responseBodyAsString = e.getResponseBodyAsString();
            LOG.info("responseBodyAsString: {}", responseBodyAsString);
            assertEquals("Name Michael is not allowed.", responseBodyAsString);
        }
    }
}
