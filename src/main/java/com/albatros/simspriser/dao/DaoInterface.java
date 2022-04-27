package com.albatros.simspriser.dao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DaoInterface<T> {

    List<T> getAll() throws InterruptedException, ExecutionException;

    void save(T obj) throws InterruptedException, ExecutionException;

    void delete(T obj) throws InterruptedException, ExecutionException;
}
