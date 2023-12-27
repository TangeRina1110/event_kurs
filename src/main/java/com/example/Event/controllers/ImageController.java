package com.example.Event.controllers;

import com.example.Event.entities.Image;
import com.example.Event.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

//    @GetMapping("/images/{id}")
//    private Image getImageById(@PathVariable Long id) {
//        Image image = imageRepository.findById(id).orElse(null);
//        return image;
//      }

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(null);
               // .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
