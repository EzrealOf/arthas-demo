package com.demo.model;

import com.example.demo.api.Animal;
import com.example.demo.model.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Duck extends Goods implements Animal<Duck> {

}
