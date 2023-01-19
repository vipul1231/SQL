package com.example.perf.client.resource;

import com.example.perf.client.ClientConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.lang.reflect.Type;
import java.net.URI;

@Getter
@Setter
public class RestResource extends Resource<Object> {
    private Type type;

    private String name;

    private Builder builder;

    private MediaType contentType;

    private HttpMethod method;

    public RestResource(String name, Builder builder, Type type) {
        this.name = name;
        this.builder = builder;
        this.type = type;
    }

    public RestResource(URI uri, Type type) {
        this.uri = uri;
        this.type = type;
    }

    public static class Builder {
        private StringBuilder urlBuilder = new StringBuilder();

        public Builder append(String path) {
            if (urlBuilder.length() > 0 && urlBuilder.lastIndexOf("/") != urlBuilder.length()) {
                urlBuilder.append("/");
            }
            urlBuilder.append(path);

            return this;
        }

        public Builder queryParam(String name, String value) {
            if (urlBuilder.lastIndexOf("?") == -1) {
                urlBuilder.append("?");
            } else {
                urlBuilder.append("&");
            }

            urlBuilder.append(name);
            urlBuilder.append("=");
            urlBuilder.append(value);

            return this;
        }

        public URI build(String endPoint) {
            if (endPoint.endsWith("/")) {
                if (urlBuilder.indexOf("/", 0) == 0) {
                    urlBuilder.insert(1, endPoint);
                } else {
                    urlBuilder.insert(0, endPoint);
                }
            } else {
                if (urlBuilder.indexOf("/", 0) == 0) {
                    urlBuilder.insert(0, endPoint);
                } else {
                    urlBuilder.insert(0, "/");
                    urlBuilder.insert(0, endPoint);
                }
            }
            return URI.create(urlBuilder.toString());
        }
    }

    public static RestResource run(String service, String URI, Object request, String... arg) {
        var urlPath = String.format(URI, (Object[]) arg);

        var resource = new RestResource(service, new Builder().append(urlPath), String.class);
        resource.setData(request);

        return resource;
    }
    public static RestResource run1(String service, String URI, Object request, String... arg) {
		var urlPath = String.format(URI, arg);

		
		RestResource resource = new RestResource(service, new Builder().append(urlPath),
		            service.equalsIgnoreCase(ClientConstants.DGM) ? byte[].class : String.class);
		        resource.setData(request);

        return resource;
      }

    public static RestResource runUNODC(String service, String URI, Object request, String... arg) {
        var resource = new RestResource(service, new Builder().append(URI), String.class);
        resource.setData(request);

        return resource;
    }
}
