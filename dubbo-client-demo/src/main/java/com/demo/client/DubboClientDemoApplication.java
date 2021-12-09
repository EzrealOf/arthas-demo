package com.demo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
public class DubboClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboClientDemoApplication.class, args);
	}

}
