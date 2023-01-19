package com.example.perf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ExecutorConfigs {

    @Bean
    public ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(10, 200,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
    }
}
