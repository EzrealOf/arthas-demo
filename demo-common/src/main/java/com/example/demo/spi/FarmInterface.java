package com.example.demo.spi;

import com.example.demo.model.FarmConfig;
import com.example.demo.model.Goods;
import com.example.demo.support.SPI;

import java.util.List;

@SPI("static")
public interface FarmInterface {

    // buy
    void buy(List<Goods> goodsList);

    //sell
    void sell(List<Goods> goodsList);

    //init
    void init(FarmConfig config);

    //destroy
    void destroy(String name);
}
