package com.example.perf.client.repository;

import com.example.perf.client.exception.ResourceException;
import com.example.perf.client.resource.ResourceProperties;
import com.example.perf.client.resource.RestResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.function.Predicate;

@Component
@Slf4j
public class RestResourceRepository<T extends RestResource> extends AbstractResourceRepository<T> {

    @Bean
    public RestTemplate restTemplate() {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(5000) // timeout to get connection from pool
                .setSocketTimeout(5000) // standard connection timeout
                .setConnectTimeout(5000) // standard connection timeout
                .build();

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig).build();

        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(requestFactory);
    }

    private HttpHeaders initHeader(T resource) {
        ResourceProperties.Url url = EndPointHelper.get(resource.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USERID","root");
        if (resource.getUri() == null) {
            URI uri = resource.getBuilder().build(url.getEndPoint());

            resource.setUri(uri);
        }
        return headers;
    }

    @Override
    public T save(T resource) {
        try {
            HttpHeaders headers = initHeader(resource);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> request = new HttpEntity<>(resource.getData(), headers);

            Object data;
            Class respClass = (Class) resource.getType();
//            log.info("RestResourceRepository calling the external service to save data with uri:::: {} request --  ", StringEscapeUtils.escapeJava(String.valueOf(resource.getUri())));
            ResponseEntity response = restTemplate().exchange(resource.getUri(), HttpMethod.POST, request, respClass);
            data = response.getBody();
            resource.setData(data);

            return resource;
        } catch (HttpServerErrorException ex) {
            String response = ex.getResponseBodyAsString();
            throw new ResourceException(response, ex);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public T update(T resource) {
        try {
            HttpHeaders headers = initHeader(resource);
            HttpEntity<Object> request = new HttpEntity<>(resource.getData(), headers);

            Object data;
            Class respClass = (Class) resource.getType();
            log.info("RestResourceRepository calling the external service to save data with uri::::" + resource.getUri() + "request -- " + request);
            ResponseEntity response = restTemplate().exchange(resource.getUri(), HttpMethod.PUT, request, respClass);
            data = response.getBody();
            resource.setData(data);

            return resource;
        } catch (HttpServerErrorException ex) {
            String response = ex.getResponseBodyAsString();
            throw new ResourceException(response, ex);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void delete(T resource) {
        try {
            HttpHeaders headers = initHeader(resource);
            HttpEntity<Object> request = new HttpEntity<>(headers);

            Class respClass = (Class) resource.getType();
            log.info("RestResourceRepository calling the external service to delete data with uri::::" + resource.getUri());
            ResponseEntity response = restTemplate().exchange(resource.getUri(), HttpMethod.DELETE, request, respClass);
            resource.setData(response.getBody());

        } catch (HttpServerErrorException ex) {
            String response = ex.getResponseBodyAsString();

            throw new ResourceException(response, ex);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public T find(T resource) {
        try {
            HttpHeaders headers = initHeader(resource);
            HttpEntity<Object> request = new HttpEntity<>(headers);

            Class respClass = (Class) resource.getType();
            log.info("RestResourceRepository calling the external service to fetch data with uri:::: {}", StringEscapeUtils.escapeJava(String.valueOf(resource.getUri())));
            ResponseEntity response = restTemplate().exchange(resource.getUri(), HttpMethod.GET, request, respClass);
            resource.setData(response.getBody());

            return resource;
        } catch (HttpServerErrorException ex) {
            String response = ex.getResponseBodyAsString();

            throw new ResourceException(response, ex);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public T find(Predicate<T> predicate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> findAll(Predicate<T> predicate) {
        throw new UnsupportedOperationException();
    }
}
