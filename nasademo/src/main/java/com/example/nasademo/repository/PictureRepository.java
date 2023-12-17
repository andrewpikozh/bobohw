package com.example.nasademo.repository;

import com.example.nasademo.db.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
