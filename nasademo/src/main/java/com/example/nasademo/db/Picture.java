package com.example.nasademo.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    private Long id;

    @Column(name = "nasa_id")
    private Long nasaId;

    @Column(name = "img_src")
    private String imgSrc;

    @ManyToOne
    @JoinColumn(name = "camera_id")
    private Camera cameraId;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Picture(Long nasaId, String imgSrc, Camera cameraId, LocalDate createdAt) {
        this.nasaId = nasaId;
        this.imgSrc = imgSrc;
        this.cameraId = cameraId;
        this.createdAt = createdAt;
    }
}
