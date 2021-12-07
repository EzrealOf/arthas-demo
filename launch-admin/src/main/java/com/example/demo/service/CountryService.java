package com.example.demo.service;

import com.example.demo.loader.ExtensionLoader;
import com.example.demo.model.FarmConfig;
import com.example.demo.process.FarmProcessor;
import com.example.demo.spi.FarmInterface;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CountryService {

    private final Map<String, FarmProcessor> farmProcessorMap = new ConcurrentHashMap<>();
    private ExtensionLoader<FarmInterface> loader;

    public void init() {
        loader = ExtensionLoader.getExtensionLoader(FarmInterface.class);

    }

    public void start(FarmConfig config) {
        String name = config.getName();
        stop(name);

        FarmProcessor farmProcessor = farmProcessorMap.computeIfAbsent(name, f -> new FarmProcessor(config));
        farmProcessor.start();

    }

    public void stop(String name) {
        synchronized (farmProcessorMap) {
            try {
                FarmProcessor processor = farmProcessorMap.remove(name);
                if (processor != null) {
                    processor.stop(name);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
