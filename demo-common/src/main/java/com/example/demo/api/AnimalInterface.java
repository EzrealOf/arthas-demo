package com.example.demo.api;

import com.example.demo.support.SPI;

@SPI("say")
public interface AnimalInterface {

    public String say(String context);
}
