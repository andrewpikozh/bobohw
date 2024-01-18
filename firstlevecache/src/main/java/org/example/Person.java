package org.example;

import lombok.*;
import org.example.annotation.Column;
import org.example.annotation.Entity;
import org.example.annotation.Id;
import org.example.annotation.Table;

@Getter
@Setter
@Entity
@Table(name = "persons")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
