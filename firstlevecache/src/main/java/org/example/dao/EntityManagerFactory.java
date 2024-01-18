package org.example.dao;

public interface EntityManagerFactory extends AutoCloseable {
    EntityManager createEntityManager();
}
