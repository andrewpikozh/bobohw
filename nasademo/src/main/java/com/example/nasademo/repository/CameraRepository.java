package com.example.nasademo.repository;

import com.example.nasademo.db.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Long> {

}
