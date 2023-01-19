package com.example.perf.v1.model.dmn.mapping;

import com.example.perf.dto.DeclarationDto;
import com.example.perf.dto.DeclarationResponseDTO;
import com.example.perf.utils.GenericRiskAssessmentHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DeclarationRiskAssessmentMapper {

    @Autowired
    GenericRiskAssessmentHelper riskAssessmentServiceHelper;

    @Autowired
    private ModelMapper mapper;

    private static final Logger LOGGER = Logger.getLogger("perf");

    public String evaluateSandboxingRiskAssessment(DeclarationResponseDTO declarationInfoResponseDTO,
                                                   Integer profileId) {
        Map<String, Long> mapOfItemLevelScore = new HashMap<>();
//        log.info("declarationInfoResponseDTO" + declarationInfoResponseDTO);
        Map<String, DeclarationDto> filteredMap = generateDeclarationOutput(
                declarationInfoResponseDTO.getDeclarationDto(), mapOfItemLevelScore, profileId);
        return filteredMap.isEmpty()
                ? null : filteredMap.entrySet().iterator().next().getKey();
    }

    private Map<String, DeclarationDto> generateDeclarationOutput(DeclarationDto declaration, Map<String, Long> mapOfItemLevelScore,
                                                                  Integer profileId) {
        Instant start = Instant.now();
        Map<String, ArrayList<Object>> map = riskAssessmentServiceHelper.evaluate(declaration, "CustomsDeclaration_" + profileId);


//        log.info("map in generateDeclarationOutput" + map.toString());
        Map<String, DeclarationDto> declaObjectMap = new HashMap<>();
        map.forEach((k, v) -> {
            declaObjectMap.put(k, filteredDeclaration(v, k, mapOfItemLevelScore));
        });

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        LOGGER.info("[RISKASSESSMENTSERVICEIMPL.evaluateSandboxingRiskAssessment] Time taken: " + duration.getSeconds());
        return declaObjectMap;
    }

    private DeclarationDto filteredDeclaration(ArrayList<?> output, String profileCode, Map<String, Long> mapOfItemLevelScore) {
        Instant start = Instant.now();
        log.info("Output Length - " + output.size());
        DeclarationDto declaration = mapper.map(output.get(0), DeclarationDto.class);
        Instant end = Instant.now();
        Duration timeTaken = Duration.between(start, end);
        LOGGER.info("[DECLARATIONMAPPER.filteredDeclaration] Time Taken: "+ timeTaken.toSeconds());
        log.info("declaration in filtered declaration" + declaration);
        return declaration;
    }
}
