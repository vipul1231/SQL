package com.example.perf.service;

import com.example.perf.dto.DeclarationDto;

import java.util.List;

/**
 * Service interface for managing Risk Assessments.
 * 
 * @author Bojjanna.Vemula
 */
public interface RiskEvaluationService {

	void doRiskEvaluation(List<DeclarationDto> declarationDtoList, Integer profileId, Integer batchId);
}
