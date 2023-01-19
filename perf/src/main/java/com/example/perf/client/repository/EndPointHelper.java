package com.example.perf.client.repository;

import com.example.perf.client.exception.ResourceException;
import com.example.perf.client.resource.ResourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;



@Component
public class EndPointHelper {
    private static ResourceProperties properties;

    @Autowired
    public EndPointHelper(ResourceProperties properties) {
        this.properties = properties;
    }

    public static String getEndPoint(String name) {
        Optional<ResourceProperties.Url> optUrl =
            properties.getUrls().stream().filter(item -> item.getName().equals(name)).findFirst();

        if (!optUrl.isPresent()) {
            throw new ResourceException("Can't find the end-point that corresponding with: " + name);
        }

        return optUrl.get().getEndPoint();
    }

    public static ResourceProperties.Url get(String name) {
        Optional<ResourceProperties.Url> optUrl =
            properties.getUrls().stream().filter(item -> item.getName().equals(name)).findFirst();

        if (!optUrl.isPresent()) {
            throw new ResourceException("Can't find the end-point that corresponding with: " + name);
        }

        return optUrl.get();
    }
}
