///*
//package com.dpw;
//
//import com.dpw.batch.Writer;
//import com.dpw.listener.JobListener;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.ThreadPoolExecutor;
//
//@Slf4j
//@Configuration
//public class BatchConfiguration {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private Reader reader;
//
//    @Autowired
//    private Writer writer;
//
//    @Autowired
//    private Processor processor;
//
//    @Autowired
//    private JobListener listener;
//
//    private static final int CHUNK = 20;
//
//
//    @Bean
//    public Job processJob() {
//        log.info("Process JOB");
//        return jobBuilderFactory.get("processJob")
//                .incrementer(new RunIdIncrementer()).listener(listener)
//                .flow(orderStep(reader, writer, processor)).end().build();
//    }
//
//
//    @Bean
//    public Step orderStep(Reader reader, Writer writer, Processor processor) {
//        log.info("BatchConfig chunkCount: {}",CHUNK);
//        return stepBuilderFactory.get("orderStep").<String, String>chunk(CHUNK)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                //.faultTolerant()
//                //.retryLimit(3)
//                //.retry(Exception.class)
//                //.faultTolerant()
//                //.skipLimit(3)
//                //.skip(Exception.class)
//                //.throttleLimit(10)
//                .taskExecutor(taskExecutor())
//                .build();
//    }
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(50);
//        log.info("Available Cores - {}",Runtime.getRuntime().availableProcessors());
//        log.info("Available Memory - {}",Runtime.getRuntime().maxMemory());
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.setThreadNamePrefix("Thread-");
//        return executor;
//    }
//}
//*/
