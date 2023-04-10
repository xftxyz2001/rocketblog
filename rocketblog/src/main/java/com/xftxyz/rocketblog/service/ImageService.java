package com.xftxyz.rocketblog.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.xftxyz.rocketblog.exception.image.ImageException;

public interface ImageService {
    String uploadImage(MultipartFile file) throws ImageException;

    Resource downloadImage(String id) throws ImageException;

    byte[] getImage(String id) throws ImageException;

    public String getIPSign();
}
