package com.example.perf.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseResponseDto
 * 
 * @author Rajib
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponseDto<T> {
	private Integer statusCode;
	private String message;
	@Builder.Default
	private List<ErrorMessage> errorMessage= new ArrayList<>();
	private T content ;
	
	
}
