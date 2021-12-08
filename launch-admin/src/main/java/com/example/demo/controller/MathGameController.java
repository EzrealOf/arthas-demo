package com.example.demo.controller;

import com.example.demo.service.MathGame;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MathGameController {


    @Resource
    private MathGame mathGame;

    @GetMapping("/startGame")
    public void startGame() {
        mathGame.startGame();

    }
}
