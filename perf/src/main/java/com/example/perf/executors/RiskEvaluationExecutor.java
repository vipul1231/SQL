package com.example.perf.executors;

import com.example.perf.batch.BatchJobDetailsService;
import com.example.perf.entity.DCDeclarationEntity;
import com.example.perf.repository.BatchJobRepository;
import com.example.perf.service.RiskEvaluationSandBox;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Slf4j
@Component
@AllArgsConstructor
public class RiskEvaluationExecutor {

    private static final Logger LOGGER = Logger.getLogger("perf");

    private final RiskEvaluationSandBox riskAssessmentService;

    private final BatchJobDetailsService batchJobDetailsService;

    private final BatchJobRepository batchJobRepository;

    private final ExecutorService executorService;

    public void runExecutorForDeclarationList(List<DCDeclarationEntity> declarationDtoList, Integer profileId, Integer batchId) {
        log.info("Received request for risk evaluation of size: {}, profileId: {}, batchId: {}", declarationDtoList.size(), profileId, batchId);
        TaskProcessor taskProcessor = new TaskProcessor(riskAssessmentService,batchJobDetailsService,batchJobRepository,
                declarationDtoList,profileId, batchId);
        executorService.submit(taskProcessor);
    }
}
