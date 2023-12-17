package com.example.nasademo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("sol")
    private Long sol;

    @JsonProperty("img_src")
    private String imgSrc;

    @JsonProperty("camera")
    private Camera camera;

    @JsonProperty("earth_date")
    private LocalDate earthDate;
}
