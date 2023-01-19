package com.dpw.batch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class Reader implements ItemReader<String> {

    private List<String> nameList;

    private final AtomicInteger count;

    @Autowired
    private ObjectMapper objectMapper;


    public Reader() {
        count = new AtomicInteger(0);
        nameList = new ArrayList<>();
        log.info("Reader Initialized....");
    }

    @Override
    public String read() {
        if (count.get() < nameList.size()) {
            return nameList.get(count.getAndIncrement());
        }
        return null;
    }

    @BeforeStep
    public void beforeStart(final StepExecution stepExecution) {
        log.info("Executing before step starts.....");
        count.set(0);
        JobParameters parameters = stepExecution.getJobExecution().getJobParameters();
        try {
            if (parameters.getString("nameList") != null) {
                nameList = objectMapper.readValue(parameters.getString("nameList"), new TypeReference<>(){});
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("Executing before step ends.....");
    }
}
