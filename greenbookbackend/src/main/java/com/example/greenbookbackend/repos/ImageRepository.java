package com.example.greenbookbackend.repos;

import com.example.greenbookbackend.entites.Image;
import com.example.greenbookbackend.entites.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {


}
