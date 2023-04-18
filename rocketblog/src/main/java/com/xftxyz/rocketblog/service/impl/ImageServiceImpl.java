package com.xftxyz.rocketblog.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.exception.image.ImageException;
import com.xftxyz.rocketblog.service.ImageService;
import com.xftxyz.rocketblog.util.Utils;

@Service
public class ImageServiceImpl implements ImageService {

    private String uploadDirectory = EnvironmentVariables.UPLOAD_DIRECTORY;

    @Override
    public String uploadImage(MultipartFile file) throws ImageException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 只允许上传图片：GIF、JPG、JPEG、PNG、SVG、WEBP、ICO
        if (!suffixName.matches("(.gif|.jpg|.jpeg|.png|.svg|.webp|.ico)$")) {
            throw new ImageException("不被允许的文件格式" + suffixName + "，只允许上传图片：GIF、JPG、JPEG、PNG、SVG、WEBP、ICO");
        }
        // UUID
        String uuid = Utils.getUUID();
        fileName = uuid + suffixName;
        Path uploadPath = Paths.get(uploadDirectory);

        try (InputStream inputStream = file.getInputStream()) {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return "/images/" + fileName;
        } catch (IOException e) {
            throw new ImageException("上传文件失败 " + fileName, e);
        }
    }

    @Override
    public Resource downloadImage(String id) throws ImageException {
        Path filePath = Paths.get(uploadDirectory).resolve(id);
        try {
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new ImageException("无法读取文件: " + id);
        }
    }

    @Override
    public byte[] getImage(String id) throws ImageException {
        Path filePath = Paths.get(uploadDirectory).resolve(id);
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new ImageException("无法读取文件: " + id);
        }
    }

    public List<String> getAllImageName() {
        try {
            return Files.list(Paths.get(uploadDirectory))
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ImageException("无法获取所有图片ID");
        }
    }

    public void deleteImageByName(String filename) throws ImageException {
        Path filePath = Paths.get(uploadDirectory).resolve(filename);
        try {
            if (!Files.exists(filePath)) {
                throw new ImageException("文件不存在: " + filename);
            }
            Files.delete(filePath);
        } catch (IOException e) {
            throw new ImageException("删除文件失败: " + filename);
        }
    }

    // 动漫IP签名档
    @Override
    public String getIPSign() {
        String baseUrl = "https://ip.ntrqq.net/images/";
        int entryIndex = Utils.random.nextInt(EnvironmentVariables.IMAGE_SIGNATURE.size());
        Map.Entry<String, List<String>> entry = EnvironmentVariables.IMAGE_SIGNATURE.entrySet()
                .stream().skip(entryIndex).findFirst().get();
        String checked = entry.getKey();

        List<String> list = entry.getValue();
        int listSize = list.size();
        int listIndex = Utils.random.nextInt(listSize);
        String diyWords = list.get(listIndex);

        String encodeWords = null;
        try {
            encodeWords = Base64.getEncoder().encodeToString(URLEncoder.encode(diyWords, "UTF-8").getBytes());
        } catch (UnsupportedEncodingException e) {
            encodeWords = "JUU2JUFDJUEyJUU4JUJGJThFJUVGJUJDJTgx"; // "欢迎"
        }

        String randomString = Long.toString(Utils.random.nextLong(), 36).substring(2);
        String queryUrl = baseUrl + checked + ".png?wd=" + encodeWords + "&r=" + randomString;
        return queryUrl;
    }

}
