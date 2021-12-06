package com.demo;

import com.example.demo.api.AnimalInterface;
import com.example.demo.support.SPI;

@SPI("duck")
public class Duck implements AnimalInterface {
    @Override
    public String say(String context) {
        return "嘎嘎嘎，我是一只鸭子，我想和大家说" + context;
    }
}
