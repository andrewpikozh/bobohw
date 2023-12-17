package com.example.nasademo.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cameras")
public class Camera {

    @Id
    private Long id;

    @Column(name = "nasa_id")
    private Long nasaId;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
