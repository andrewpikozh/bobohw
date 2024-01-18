package org.example;

import org.example.dao.Session;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        var dataSource = initDB();
        var session = new Session(dataSource, Person.class);
        Person person1 = session.find(Person.class, 1L).get();
        System.out.println(person1);
        Person person2 = session.find(Person.class, 1L).get();
        System.out.println(person2);
        System.out.println(person1 == person2);
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