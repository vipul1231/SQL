package com.example.perf.client.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.resource")
@Component
public class ResourceProperties {
    private File file;

    private List<Url> urls;

    @Getter
    @Setter
    public static class File {
        private String path;
    }

    @Getter
    @Setter
    public static class Url {
        private String name;

        private String endPoint;

        private String username;

        private String password;

        private String xApiKey;
    }
}
