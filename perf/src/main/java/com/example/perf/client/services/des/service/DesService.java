package com.example.perf.client.services.des.service;

import com.example.perf.client.services.des.domain.DesResponse;

public interface DesService {

	<T> DesResponse evaluateData(T request, String... arg);
	
}
