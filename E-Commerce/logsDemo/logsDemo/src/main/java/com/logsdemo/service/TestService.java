package com.logsdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    Logger logger = LoggerFactory.getLogger(TestService.class);

    public String test() {

        logger.info("This is an info log");
        logger.warn("This is an warning log");
        logger.error("This is an error log");
        logger.debug("This is an debug log");
        logger.trace("This is an Trace log");

        return "This is an test service method";
    }
}
