package com.example.greenbookbackend.services;

import com.example.greenbookbackend.entites.Image;
import com.example.greenbookbackend.repos.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
