package de.fred4jupiter.spring.exception.handling.rest;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {

    private static final Logger LOG = LoggerFactory.getLogger(CustomErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        final String errorMessage = parseErrorMessageFromBody(response);

        if (response.status() >= 400 && response.status() <= 499) {
            return new HttpClientErrorException(HttpStatus.valueOf(response.status()), errorMessage);
        }
        if (response.status() >= 500 && response.status() <= 599) {
            return new HttpServerErrorException(HttpStatus.valueOf(response.status()), errorMessage);
        }
        return FeignException.errorStatus(methodKey, response);
    }

    private String parseErrorMessageFromBody(Response response) {
        try {
            return IOUtils.toString(response.body().asReader());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return "No error message available";
    }
}
