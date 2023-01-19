package com.example.perf.v1.model.dmn.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileIndicatorsDto {
	private String category;

	private String attributeDescription;

	private String controlType;

	private boolean displayCode;

	private String entityAlias;

	private Integer id;

	private String refTypeCode;

	private String type;
	
	private String attribute;

}