package com.example.perf.utils;


import com.example.perf.client.services.des.domain.DesResponse;
import com.example.perf.client.services.des.service.DesService;
import com.example.perf.dto.DmnInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * @author gaurav.verma
 */

@Service
@Slf4j
public class GenericRiskAssessmentHelper {

	@Autowired
	ObjectMapper objMapper;

	@Autowired
	private DesService desService;
    
	/**
	 * This method is used to match the rule from dmn.
	 *
	 * @param eval
	 * @return RiskAssDeclaration
	 */

	private static final Logger LOGGER = Logger.getLogger("perf");

	public Map<String, ArrayList<Object>> evaluate(Object eval, String category) {
		log.info("Eval: {}, category: {}", eval, category);
		Instant start = Instant.now();
		ArrayList<Object> main = new ArrayList<>();
		HashMap<String, ArrayList<Object>> profilesMap = new HashMap<>();
		main.add(eval);
		DmnInput input = new DmnInput();
		input.setMain(main);
		DesResponse mainResponse = desService.evaluateData(input, category);
		List<Map<String, Object>> mainResult = mainResponse.getResultList();

		mainResult.forEach(man -> {
			String profileCode = man.get(ApplicationConstants.PROFILECODE) != null ? man.get(ApplicationConstants.PROFILECODE).toString() : null;
			if(profileCode!=null) {
				ArrayList<?> x= (ArrayList<?>) man.get(ApplicationConstants.OUTPUT);
				profilesMap.put(profileCode, (ArrayList<Object>) x);
				//DeclarationEntity declaration = filteredDeclaration(x);
				//profilesDeclMap.put(profileCode, declaration);
			}
	
		});
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);
		if (duration.toSeconds() > 0){
			LOGGER.info("[GENERICRISKASSESSMENTHELPER] Time taken: "+ duration.toSeconds());
		}
		return profilesMap;
	}
}