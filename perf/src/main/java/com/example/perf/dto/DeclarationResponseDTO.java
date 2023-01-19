package com.example.perf.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DeclarationResponseDTO {
	
	protected DeclarationDto declarationDto;

	protected RiskAssDeclaration riskAssDeclaration;

}
