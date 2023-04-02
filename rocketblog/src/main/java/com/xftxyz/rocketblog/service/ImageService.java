package com.xftxyz.rocketblog.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile file) throws IOException;

    Resource downloadImage(String id) throws IOException;

    byte[] getImage(String id) throws IOException;
}
