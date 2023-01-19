package com.example.perf.client.services.des.service.impl;

import com.example.perf.client.ClientConstants;
import com.example.perf.client.exception.ResourceException;
import com.example.perf.client.repository.ResourceRepository;
import com.example.perf.client.resource.RestResource;
import com.example.perf.client.services.des.domain.DesResponse;
import com.example.perf.client.services.des.service.DesService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class DesServiceImpl implements DesService {

    private ResourceRepository<RestResource> resourceRepository;

    @Autowired
    public DesServiceImpl(ResourceRepository<RestResource> resourceRepository) {
        this.resourceRepository = resourceRepository;
    }
	
	@Override
	public <T> DesResponse evaluateData(T request, String... arg) {
        RestResource resource = RestResource.run(ClientConstants.DES, ClientConstants.DES_EVALUATE_URI, request, arg);
//        log.debug("Fetching Data from {} for evaluate with request {} :" ,StringEscapeUtils.escapeJava(String.valueOf(resource.getUri())),StringEscapeUtils.escapeJava(String.valueOf(request)));
        RestResource restResourceApp = resourceRepository.save(resource);

        ObjectMapper objectMapper = new ObjectMapper();

        DesResponse response = null;
        try {

            log.debug(ClientConstants.DES_RESPONSE,StringEscapeUtils.escapeJava(String.valueOf(restResourceApp.getData())));
            response = objectMapper.readValue((String) restResourceApp.getData(), DesResponse.class);
        	log.debug("Response object from response to evaluate:" + response);            
        } catch (JsonParseException e) {
        	 log.error(ClientConstants.DES_EVALUATE_RESOURCEEXCEPTION + e);
            throw new ResourceException(e);
        } catch (JsonMappingException e) {
        	log.error(ClientConstants.DES_EVALUATE_RESOURCEEXCEPTION + e);
            throw new ResourceException(e);
        } catch (IOException e) {
        	log.error(ClientConstants.DES_EVALUATE_RESOURCEEXCEPTION + e);
            throw new ResourceException(e);
        }
        return response;
    }

}
