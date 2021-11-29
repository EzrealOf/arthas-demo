package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${text:World}")
    public String text;

    @GetMapping("/hello")
    public String hello() {
        logger.info("hello info");

        logger.debug("hello debug");

        return "hello " + text;
    }
}
