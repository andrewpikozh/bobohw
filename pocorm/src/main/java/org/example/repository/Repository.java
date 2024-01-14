package org.example.repository;

import java.util.Optional;

public interface Repository<T, U> {
    Optional<T> findById(U id);
}
