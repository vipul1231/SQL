package com.example.perf.client.repository;

import com.example.perf.client.exception.ResourceException;
import com.example.perf.client.resource.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;



public abstract class AbstractResourceRepository<T extends Resource<?>> implements ResourceRepository<T> {
    public abstract T save(T resource);

    public abstract void delete(T resource);

    public abstract T find(T resource);

    public abstract T find(Predicate<T> predicate);

    public abstract Iterable<T> findAll();

    public abstract Iterable<T> findAll(Predicate<T> predicate);

    @Override
    public Iterable<T> saveAll(Iterable<T> resources) {
        List results = new ArrayList<>();
        try {
            for (T resource : resources) {
                T result = this.save(resource);
                results.add(result);
            }

            return results;
        } catch (Exception ex) {
            // this.deleteAll(results);
            throw new ResourceException(ex);
        }
    }

    @Override
    public void delete(Predicate<T> predicate) {
        T resource = this.find(predicate);
        if (resource == null) {
            throw new ResourceException("Can't find resource with condition: " + predicate.toString());
        } else {
            this.delete(resource);
        }
    }

    @Override
    public void deleteAll(Iterable<T> resources) {
        List deleted = new ArrayList<>();
        try {
            for (T resource : resources) {
                this.delete(resource);
                deleted.add(resource);
            }
        } catch (Exception ex) {
            // this.saveAll(deleted);
            throw new ResourceException(ex);
        }
    }

    @Override
    public void deleteAll(Predicate<T> predicate) {
        Iterable<T> resources = this.findAll(predicate);
        this.deleteAll(resources);
    }
}
