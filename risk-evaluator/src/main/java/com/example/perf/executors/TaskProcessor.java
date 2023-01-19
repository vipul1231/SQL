package com.example.perf.executors;

import com.example.perf.batch.BatchJobDetailsService;
import com.example.perf.entity.BatchJobDetails;
import com.example.perf.entity.BatchJobResult;
import com.example.perf.entity.DCDeclarationEntity;
import com.example.perf.mapper.DCDeclarationMapper;
import com.example.perf.repository.BatchJobRepository;
import com.example.perf.service.RiskEvaluationSandBox;
import com.example.perf.service.RiskEvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class TaskProcessor implements Callable<Void> {

    private static final Logger LOGGER = Logger.getLogger("perf");

    private final List<DCDeclarationEntity> entityList;

    private final Integer profileId;

    private final Integer batchId;

    private final RiskEvaluationSandBox riskEvaluationService;

    private final BatchJobDetailsService batchJobDetailsService;

    private final BatchJobRepository batchJobRepository;

    public TaskProcessor(RiskEvaluationSandBox riskEvaluationService,
                         BatchJobDetailsService batchJobDetailsService,
                         BatchJobRepository batchJobRepository,
                         List<DCDeclarationEntity> entityList,
                         Integer profileId,
                         Integer batchId) {
        this.riskEvaluationService = riskEvaluationService;
        this.batchJobDetailsService = batchJobDetailsService;
        this.batchJobRepository = batchJobRepository;
        this.entityList = entityList;
        this.profileId = profileId;
        this.batchId = batchId;
    }

    @Override
    public Void call() throws Exception {

        try {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            int size  = entityList.size();
            Instant completeProcessTimeStart = Instant.now();
            for (DCDeclarationEntity entity : entityList) {
                int count = atomicInteger.getAndIncrement();
                Instant start = Instant.now();
                LOGGER.info("ITERATOR for counter: "+ count+", size: "+ size);
                String profileCode = riskEvaluationService.calculateRiskAssDeclarationSyncFlatSandbox(DCDeclarationMapper.INSTANCE.dcDeclarationEntityToDto(entity), profileId);

                if (StringUtils.isNoneEmpty(profileCode)) {
                    List<BatchJobResult> results = new ArrayList<>();
                    if (Objects.nonNull(entity)) {
                        log.info("Profile HIT {}", entity.getDeclarationId());
                        BatchJobResult batchJobResult = new BatchJobResult();
                        batchJobResult.setDeclarationNumber(entity.getDeclarationNo());
                        batchJobResult.setDeclarationType(entity.getDeclarationTypeName());
                        batchJobResult.setRegime(entity.getRegimeTypeName());
                        batchJobResult.setSubmittedDate(new Date());
                        batchJobResult.setTraderName(null);
                        batchJobResult.setVersion(null);
                        batchJobResult.setAgencyName(null);
                        batchJobResult.setDeclarationId(entity.getDeclarationId());
                        batchJobResult.setHit(true);
                        results.add(batchJobResult);
                    }

                    if (results.size() > 0) {
                        CompletableFuture.runAsync(() -> {
                            Optional<BatchJobDetails> jobDetailsOptional = batchJobDetailsService.fetchDeclarationBatchJobDetailsTxn(batchId);
                            if (jobDetailsOptional.isPresent()) {
                                jobDetailsOptional.get().getBatchJobResults().addAll(results);
                                jobDetailsOptional.get().setTotalNoOfHits(jobDetailsOptional.get().getTotalNoOfHits() + results.size());
                                batchJobRepository.save(jobDetailsOptional.get());
                            }
                        });
                    }
                }
                Instant endTime = Instant.now();
                Duration timeTaken = Duration.between(start, endTime);
                LOGGER.info("[PROCESSOR.call] Time taken: "+ timeTaken.toSeconds());
            }
            Instant completeProcessTimeEnd = Instant.now();
            Duration timeTaken = Duration.between(completeProcessTimeStart, completeProcessTimeEnd);
            LOGGER.info("Complete list size: "+size+", time taken: "+timeTaken.toSeconds());
        } catch (Exception e) {
            log.error("Some Exception occurred ", e);
        }

        return Void.TYPE.newInstance();
    }
}
