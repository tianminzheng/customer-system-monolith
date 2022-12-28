package org.geekbang.projects.cs.infrastructure.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignErrorDecoder extends ErrorDecoder.Default {
    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = super.decode(methodKey, response);
        logger.error(exception.getMessage(), exception);
        return exception;
    }
}
