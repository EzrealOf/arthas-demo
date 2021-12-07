package com.demo.service.impl;

import com.example.demo.model.FarmConfig;
import com.example.demo.model.Goods;
import com.example.demo.spi.FarmInterface;
import com.example.demo.support.SPI;

import java.util.List;

@SPI("quick")
public class DuckFarm implements FarmInterface {

    String name;

    @Override
    public void buy(List<Goods> goodsList) {

    }

    @Override
    public void sell(List<Goods> goodsList) {

    }

    @Override
    public void init(FarmConfig config) {
        this.name = config.getName();

    }

    @Override
    public void destroy(String name) {

    }
}
