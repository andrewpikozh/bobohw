package com.example.nasademo.client;

import com.example.nasademo.model.Photos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nasa", url = "${nasa.url}")
public interface NasaClient {
    @GetMapping(path = "/photos")
    Photos getNasaPhotos(
            @RequestParam("sol") Long sol,
            @RequestParam("api_key") String apiKey
    );
}
