package com.example.demo.process;

import com.example.demo.loader.ExtensionLoader;
import com.example.demo.api.Animal;
import com.example.demo.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CountryProcessor {

    private static final Logger logger = LoggerFactory.getLogger(CountryProcessor.class);

    private volatile boolean running = false;
    private Thread thread = null;
    private List<Animal> animalList = new ArrayList<>();
    private Thread.UncaughtExceptionHandler handler = (t, e) -> logger
            .error("parse events has an error", e);

    public CountryProcessor() {
        ExtensionLoader<Animal> loader = new ExtensionLoader<>(Animal.class);

    }

    public void start() {
        if (!running) {
            thread = new Thread(this::process);
            thread.setUncaughtExceptionHandler(handler);
            thread.start();
            running = true;
        }
    }

    private void process() {
        while (!running) {
            ThreadUtil.sleep(1000);
        }
        while (running) {

        }
    }
}
