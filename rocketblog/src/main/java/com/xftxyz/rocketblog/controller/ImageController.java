package com.xftxyz.rocketblog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xftxyz.rocketblog.exception.image.ImageException;
import com.xftxyz.rocketblog.service.ImageService;

/**
 * 图片相关
 */
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     * 上传图片。
     *
     * @param file 要上传的图片文件
     * @return 返回一个字符串，表示图片上传成功后的 URL
     * @throws ImageException 如果上传图片失败，则会抛出 ImageException 异常
     */
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageService.uploadImage(file);
            return imageUrl;
        } catch (IOException e) {
            throw new ImageException("上传图片失败");
        }
    }

    /**
     * 下载指定 id 的图片。
     *
     * @param id 要下载的图片的 id
     * @return 返回一个 ResponseEntity<Resource> 对象，包含要下载的图片资源
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadImage(@PathVariable("id") String id) {
        try {
            Resource imageResource = imageService.downloadImage(id);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + imageResource.getFilename() + "\"").body(imageResource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 获取指定 id 的图片的字节数组。
     *
     * @param id 要获取的图片的 id
     * @return 返回一个 ResponseEntity<byte[]> 对象，包含指定 id 的图片的字节数组
     */
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        try {
            byte[] imageData = imageService.getImage(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 获取IP签名档图片的URL。
     *
     * @return 返回一个字符串，表示IP签名档图片的URL
     */
    @GetMapping("/ipsign")
    public String getIpSign() {
        return imageService.getIPSign();
    }

}
