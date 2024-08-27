package com.example.greenbookbackend.controllers;

import com.example.greenbookbackend.entites.Image;
import com.example.greenbookbackend.repos.ImageRepository;
import com.example.greenbookbackend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/add")
    public ResponseEntity<Image> addImage(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("file") MultipartFile file) throws IOException, IOException {

        Image image = new Image();
        image.setName(name);
        image.setType(type);
        image.setData(file.getBytes());

        Image savedImage = imageService.saveImage(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedImage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Image image = imageService.getImageById(id);
        if (image != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(image.getType()));
            headers.setContentLength(image.getData().length);
            return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
