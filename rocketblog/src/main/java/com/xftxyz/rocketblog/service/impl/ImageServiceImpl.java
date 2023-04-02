package com.xftxyz.rocketblog.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xftxyz.rocketblog.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    private String uploadDirectory = "uploads";

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // UUID
        String uuid = UUID.randomUUID().toString();
        fileName = uuid + suffixName;
        Path uploadPath = Paths.get(uploadDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            // return "http://8.130.81.23:8080/images/" + fileName;
            return "/images/" + fileName;
        } catch (IOException e) {
            throw new IOException("Failed to save file " + fileName, e);
        }
    }

    public Resource downloadImage(String id) throws IOException {
        Path filePath = Paths.get(uploadDirectory).resolve(id);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException("Could not read file: " + id);
        }
    }

    @Override
    public byte[] getImage(String id) throws IOException {
        Path filePath = Paths.get(uploadDirectory).resolve(id);
        return Files.readAllBytes(filePath);
    }

}
