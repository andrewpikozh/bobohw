package com.example.nasademo.controller;

import com.example.nasademo.model.Sol;
import com.example.nasademo.service.NasaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nasa")
@RequiredArgsConstructor
public class NasaController {
    private final NasaService nasaService;

    @PostMapping("/pictures/stealing")
    public ResponseEntity stealing(@RequestBody Sol request) {
        nasaService.steal(request);
        return ResponseEntity.ok().build();
    }
}
