package com.example.perf.v1;

import com.example.perf.dto.BaseResponseDto;
import com.example.perf.dto.DeclarationDto;
import com.example.perf.service.RiskEvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Risk Profile controller
 *
 * @author Riyasat Ali
 */
@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class RiskProfileController {

    @Autowired
    private RiskEvaluationService riskProfileService;


    /**
     * Method used to perform the Sand Boxing.
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/evaluate/profile/{profileId}/batch/{batchId}")
    public BaseResponseDto<Object> performSandBoxing(@RequestBody List<DeclarationDto> declarationDtoList,
                                                     @PathVariable Integer profileId, @PathVariable Integer batchId) {

        riskProfileService.doRiskEvaluation(declarationDtoList, profileId, batchId);
        return BaseResponseDto.<Object>builder().message("SUCCESS").statusCode(HttpStatus.OK.value()).build();
    }


}
