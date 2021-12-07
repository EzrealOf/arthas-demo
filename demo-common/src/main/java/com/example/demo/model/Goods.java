package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods implements Serializable {

    private String name;

    private String price;

}
