package com.example.demo.process;

import com.example.demo.loader.ExtensionLoader;
import com.example.demo.model.FarmConfig;
import com.example.demo.spi.FarmInterface;
import com.example.demo.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FarmProcessor {

    private static final Logger logger = LoggerFactory.getLogger(FarmProcessor.class);

    private static final String CONNECTOR_SPI_DIR = "/plugin";
    private static final String CONNECTOR_STANDBY_SPI_DIR = "/classes/plugin";

    private FarmInterface farmInterface;
    private String farmName;

    private volatile boolean running = false;
    private Thread thread = null;
    private Thread.UncaughtExceptionHandler handler = (t, e) -> logger
            .error("parse events has an error", e);

    public FarmProcessor(FarmConfig config) {
        this.farmName = config.getName();
        ExtensionLoader<FarmInterface> loader = new ExtensionLoader<>(FarmInterface.class);
        farmInterface = loader.getExtension(config.getName().toLowerCase(), CONNECTOR_SPI_DIR, CONNECTOR_STANDBY_SPI_DIR);

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(farmInterface.getClass().getClassLoader());
        farmInterface.init(config);
        Thread.currentThread().setContextClassLoader(cl);

    }

    public void start() {
        if (!running) {
            thread = new Thread(this::process, farmName);
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
            logger.info("-------{} is staring", farmName);
            ThreadUtil.sleep(1000);
        }
    }

    public void stop(String name){

    }
}
