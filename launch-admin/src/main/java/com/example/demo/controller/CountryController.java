package com.example.demo.controller;

import com.example.demo.model.FarmConfig;
import com.example.demo.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Resource
    private CountryService countryService;


    @GetMapping("/initFarm")
    @ResponseBody
    public String initFarm(@RequestParam("name") String name, @RequestParam("type") String type) {
        FarmConfig config = FarmConfig.builder().name(name).type(type).build();
        countryService.start(config);
        return "success";
    }

}
