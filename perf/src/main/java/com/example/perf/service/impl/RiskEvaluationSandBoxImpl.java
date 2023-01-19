package com.example.perf.service.impl;

import com.example.perf.dto.*;
import com.example.perf.service.RiskEvaluationSandBox;
import com.example.perf.utils.ApplicationConstants;
import com.example.perf.utils.CommonUtil;
import com.example.perf.v1.model.dmn.mapping.DeclarationRiskAssessmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class RiskEvaluationSandBoxImpl implements RiskEvaluationSandBox {

    @Autowired
    private DeclarationRiskAssessmentMapper declarationRiskAssessmentMapper;

    @Override
    public String calculateRiskAssDeclarationSyncFlatSandbox(DeclarationDto declarationDto, Integer profileId) {
        //        log.info("Start the RISK ASS Sync V2 Process ");
        try {
            Instant start = Instant.now();
            DeclarationResponseDTO declarationInfoResponseDTO = mappingRiskAssDeclarationFlat(declarationDto);
//            log.info("New Implimentation....of DMN....");

            String assesedProfileResult = declarationRiskAssessmentMapper.evaluateSandboxingRiskAssessment(declarationInfoResponseDTO, profileId);
            Instant end = Instant.now();

            Duration timeTaken = Duration.between(start, end);
            log.info("[RISKASSESSMENTSERVICEIMPL.calculateRiskAssDeclarationSyncFlatSandbox] Time taken: " + timeTaken.toSeconds());
//            log.info(assesedProfileResult);
            return assesedProfileResult;
        } catch (DataAccessException ex) {
            log.error(ApplicationConstants.DATA_ACCESS_EXCEPTION, ex);
            ErrorMessage errMessage = CommonUtil.createErrorMessage(ErrorCode.RASRECORDNOTSAVED.getCode(),
                    ErrorCode.RASRECORDNOTSAVED.getShortDescription(), ex.toString());
            throw new BusinessException(errMessage);
        } catch (Exception ex) {
            log.error(ApplicationConstants.RISK_ASSESS_EXCEPTION, ex);
            ErrorMessage errMessage = CommonUtil.createErrorMessage(ErrorCode.RASINTERNALSERVERERROR.getCode(),
                    ErrorCode.RASINTERNALSERVERERROR.getShortDescription(), ex.toString());
            throw new BusinessException(errMessage);
        }
    }

    private DeclarationResponseDTO mappingRiskAssDeclarationFlat(DeclarationDto request) {
//        log.info("CUSTOM- DECL DATA - " + request);
        request.setValuationStatus(null);
        ;
        DeclarationResponseDTO declarationInfoResponseDTO = new DeclarationResponseDTO();
        declarationInfoResponseDTO.setDeclarationDto(request);
        RiskAssDeclaration riskAssDeclaration = new RiskAssDeclaration();
        riskAssDeclaration.setCreatedBy(ApplicationConstants.CREATEDBY);
        riskAssDeclaration.setCreatedDate(LocalDate.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        // TODO MONK 01 riskAssDeclaration.setDateOfarrival(LocalDate.parse(request.getArrivalDate(), formatter));
        String importer = null;

        if (request.getRegimeTypeId().equals("1"))
            importer = request.getInboundCargoChannelName();
        else
            importer = request.getOutboundCargoChannelName();

        riskAssDeclaration.setImporter(importer);
        riskAssDeclaration.setProcedure(request.getDeclarationTypeId() + "");
        riskAssDeclaration.setReferanceId(request.getDeclarationId());
        riskAssDeclaration.setRegime(request.getRegimeTypeId() + "");
        riskAssDeclaration.setStatus(ApplicationConstants.RISK_ASS_STATUS);
        riskAssDeclaration.setReferenceCode(request.getDeclarationRefNo());
        declarationInfoResponseDTO.setRiskAssDeclaration(riskAssDeclaration);
        return declarationInfoResponseDTO;
    }
}
