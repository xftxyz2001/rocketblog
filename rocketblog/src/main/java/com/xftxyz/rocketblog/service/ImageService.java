package com.xftxyz.rocketblog.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.xftxyz.rocketblog.exception.image.ImageException;

import jakarta.validation.constraints.NotBlank;

public interface ImageService {
    String uploadImage(MultipartFile file) throws ImageException;

    Resource downloadImage(String id) throws ImageException;

    byte[] getImage(String id) throws ImageException;

    public String getIPSign();

    List<String> getAllImageIds();

    void deleteImage(@NotBlank(message = "id不能为空") String id);
}
