package com.example.demo.controller;

import com.example.demo.model.FarmConfig;
import com.example.demo.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Resource
    private CountryService countryService;


    @GetMapping("/initFarm")
    public String initFarm(@RequestParam("name") String name) {
        FarmConfig config = FarmConfig.builder().name(name).build();
        countryService.start(config);
        return "success";
    }

}
