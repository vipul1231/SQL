package com.example.perf.service;

import com.example.perf.dto.DeclarationDto;

public interface RiskEvaluationSandBox {

    String calculateRiskAssDeclarationSyncFlatSandbox(DeclarationDto declarationDto, Integer profileId);
}
