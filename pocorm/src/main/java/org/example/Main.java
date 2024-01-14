package org.example;

import org.example.repository.JDBCRepository;
import org.example.repository.Repository;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        var dataSource = initDB();
        Repository<Person, Long> repository = new JDBCRepository<>(dataSource, Person.class);
        Person person = repository.findById(1L).get();
        System.out.println(person);
    }

    public static DataSource initDB() {
        var dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/hw22");
        dataSource.setDatabaseName("hw22");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin");
        return dataSource;
    }
}