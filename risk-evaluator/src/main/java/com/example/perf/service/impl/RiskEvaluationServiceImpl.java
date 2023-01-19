package com.example.perf.service.impl;

import com.example.perf.dto.DeclarationDto;
import com.example.perf.entity.DCDeclarationEntity;
import com.example.perf.executors.RiskEvaluationExecutor;
import com.example.perf.mapper.DCDeclarationMapper;
import com.example.perf.service.RiskEvaluationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class RiskEvaluationServiceImpl implements RiskEvaluationService {

    private static final Logger LOGGER = Logger.getLogger("perf");

    @Autowired
    private RiskEvaluationExecutor riskEvaluationExecutor;

    @Override
    public void doRiskEvaluation(List<DeclarationDto> declarationDtoList, Integer profileId, Integer batchId) {
        List<DCDeclarationEntity> declarationEntities = DCDeclarationMapper.INSTANCE.declarationEntityListToDto(declarationDtoList);
        riskEvaluationExecutor.runExecutorForDeclarationList(declarationEntities, profileId, batchId);
    }
}
