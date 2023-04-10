package com.xftxyz.rocketblog.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.service.ImageService;
import com.xftxyz.rocketblog.util.Utils;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    RestTemplate restTemplate;

    private String uploadDirectory = EnvironmentVariables.UPLOAD_DIRECTORY;

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // UUID
        String uuid = Utils.getUUID();
        fileName = uuid + suffixName;
        Path uploadPath = Paths.get(uploadDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
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

        String diyWords = null;
        if (listSize > 0) {
            int listIndex = Utils.random.nextInt(listSize);
            diyWords = list.get(listIndex);
        } else {
            @SuppressWarnings("rawtypes")
            ResponseEntity<Map> responseEntity = restTemplate.getForEntity("https://v1.hitokoto.cn/?encode=json",
                    Map.class);
            @SuppressWarnings("rawtypes")
            Map map = responseEntity.getBody();

            diyWords = map != null ? (String) map.get("hitokoto") : "欢迎";
        }
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
