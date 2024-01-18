package org.example.dao;

import java.util.Optional;

public interface EntityManager extends AutoCloseable {
    <T> Optional<T> find(Class<T> clazz, Object primaryKey);
}
