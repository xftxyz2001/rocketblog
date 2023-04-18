package com.xftxyz.rocketblog.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.xftxyz.rocketblog.exception.image.ImageException;
import com.xftxyz.rocketblog.pojo.FileInfo;

public interface ImageService {
    String uploadImage(MultipartFile file) throws ImageException;

    Resource downloadImage(String id) throws ImageException;

    byte[] getImage(String id) throws ImageException;

    public String getIPSign();

    List<FileInfo> getAllImageFileInfo();

    void deleteImageByName(String filename);
}
