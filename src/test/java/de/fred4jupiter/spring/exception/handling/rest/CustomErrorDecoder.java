package de.fred4jupiter.spring.exception.handling.rest;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {

    private static final Logger LOG = LoggerFactory.getLogger(CustomErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        String errorMessage = parseErrorMessageFromBody(response);
        return new HttpClientErrorException(HttpStatus.valueOf(response.status()), errorMessage);
    }

    private String parseErrorMessageFromBody(Response response) {
        try {
            return IOUtils.toString(response.body().asReader());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return "n.A.";
    }
}
