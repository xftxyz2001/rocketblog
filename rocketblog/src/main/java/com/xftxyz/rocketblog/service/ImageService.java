package com.xftxyz.rocketblog.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.xftxyz.rocketblog.pojo.FileInfo;

public interface ImageService {
    String uploadImage(MultipartFile file);

    Resource downloadImage(String id);

    byte[] getImage(String id);

    public String getIPSign();

    List<FileInfo> getAllImageFileInfo(int pageNum, int pageSize);

    void deleteImageByName(String filename);

    public byte[] getLowResolutionImage(String id, int newWidth, int newHeight);

    List<String> uploadImages(MultipartFile[] files);
}
