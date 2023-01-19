package com.example.perf.client.resource;

import lombok.Data;

import java.net.URI;

@Data
public abstract class Resource<T> {
    protected String name;

    protected URI uri;

    protected T data;
}
