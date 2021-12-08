package com.example.demo.service;

import com.example.demo.DemoApplicationTests;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class MathGameTest extends DemoApplicationTests {
    @Resource
    private MathGame mathGame;

    @Test
    void startGame() {
        mathGame.startGame();
    }
}