package com.example.demo.controller;

import com.example.demo.process.CountryProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Resource
    private CountryProcessor zooProcessor;


    public String initFarm() {

        return "success";
    }

}
