package com.logsdemo.controller;

import com.logsdemo.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    TestService testService;

    @GetMapping
    public String get() {


//        Default -> info, warn, error
//        ALL<TRACE<DEBUG<INFO<WARN<ERROR<FATAL<OFF


//        ERROR -> error
//        WARN -> warn, error
//        INFO -> Info, warn , error  -> Spring boot default
//        DEBUG -> info, warn, error, debug
//        TRACE -> info, warn, error, debug

        logger.info("This is an info log for testing");
        logger.warn("This is an warning log");
        logger.error("This is an error log");
        logger.debug("This is an debug log");
        logger.trace("This is an Trace log");


        // Logging sequence

        String mes = testService.test();

        System.out.println("This is an controller class");

        return "Hello we are learning Logging framework in SB  " + mes;
    }

}
