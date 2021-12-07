package com.example.demo.service;

import com.example.demo.DemoApplicationTests;
import com.example.demo.model.FarmConfig;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class CountryServiceTest extends DemoApplicationTests {
    @Resource
    private CountryService countryService;

    @Test
    void start() {
        FarmConfig config = FarmConfig.builder().name("quick").build();
        countryService.start(config);
    }

    @Test
    void stop() {
    }
}