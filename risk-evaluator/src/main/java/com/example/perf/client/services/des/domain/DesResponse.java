package com.example.perf.client.services.des.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DesResponse {
	List<Map<String, Object>> resultList;
}
