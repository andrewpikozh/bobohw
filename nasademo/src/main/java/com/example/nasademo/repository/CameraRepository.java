package com.example.nasademo.repository;

import com.example.nasademo.db.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CameraRepository extends JpaRepository<Camera, Long> {
    Optional<Camera> findByNasaId(Long nasaId);
}
