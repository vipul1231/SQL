package com.dpw.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Processor implements ItemProcessor<String, String> {

    public Processor() {
        log.info("Processor Initialized....");
    }

    @Override
    public String process(String item) throws Exception {
        log.info("Received item: {}", item);
        return item.toUpperCase();
    }
}
