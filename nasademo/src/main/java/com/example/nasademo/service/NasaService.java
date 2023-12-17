package com.example.nasademo.service;

import com.example.nasademo.client.NasaClient;
import com.example.nasademo.db.Camera;
import com.example.nasademo.model.Sol;
import com.example.nasademo.repository.CameraRepository;
import com.example.nasademo.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NasaService {
    private final NasaClient nasaClient;
    private final CameraRepository cameraRepository;
    private final PictureRepository pictureRepository;

    @Value("${nasa.key}")
    private String apiKey;

    public void steal(Sol request) {
        var nasaPhotos = nasaClient.getNasaPhotos(request.getSol(), apiKey);
        nasaPhotos.getPhotoList()
                .forEach(photo -> {
                    var camera = photo.getCamera();
                    cameraRepository.save(new Camera(camera.getId(), photo.getId(), camera.getName(), photo.getEarthDate()));
                    //TODO: add ability to save pictures
//                    Optional<Camera> cameraByNasaId = cameraRepository.findByNasaId(photo.getId());
//                    pictureRepository.save(new Picture(photo.getId(), photo.getImgSrc(), cameraByNasaId.get(), photo.getEarthDate()));
                });
    }
}
