package com.example.perf.client.repository;

import com.example.perf.client.resource.Resource;

import java.util.function.Predicate;



public interface ResourceRepository<T extends Resource> {

    T save(T resource);

    T update(T resource);

    void delete(T resource);

    void delete(Predicate<T> predicate);

    T find(T resource);

    T find(Predicate<T> predicate);

    Iterable<T> saveAll(Iterable<T> resources);

    void deleteAll(Iterable<T> resources);

    void deleteAll(Predicate<T> predicate);

    Iterable<T> findAll();

    Iterable<T> findAll(Predicate<T> predicate);

}
