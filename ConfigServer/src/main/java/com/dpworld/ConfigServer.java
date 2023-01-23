package com.dpworld;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@EnableBatchProcessing
@SpringBootApplication(scanBasePackages = "com.dpw")
public class ConfigServer {

    public static void main(String[] args) throws InterruptedException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date d1 = sdf.parse("2023-01-19T21:12:25");

        Date d2 = sdf.parse("2023-01-19T21:09:46");

        long secs = (d1.getTime() - d2.getTime()) / 1000;
        long hours = secs;

        System.out.println(hours);

        SpringApplication.run(ConfigServer.class, args);
    }
}
