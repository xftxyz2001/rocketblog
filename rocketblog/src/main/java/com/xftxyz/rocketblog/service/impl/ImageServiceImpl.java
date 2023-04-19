package com.xftxyz.rocketblog.service.impl;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.exception.image.ImageException;
import com.xftxyz.rocketblog.pojo.FileInfo;
import com.xftxyz.rocketblog.service.ImageService;
import com.xftxyz.rocketblog.util.Utils;

@Service
public class ImageServiceImpl implements ImageService {

    private String uploadDirectory = EnvironmentVariables.UPLOAD_DIRECTORY;

    @Override
    public String uploadImage(MultipartFile file) {
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
    public List<String> uploadImages(MultipartFile[] files) {
        return List.of(files).stream().map(file -> uploadImage(file)).collect(Collectors.toList());
    }

    @Override
    public Resource downloadImage(String id) {
        Path filePath = Paths.get(uploadDirectory).resolve(id);
        try {
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new ImageException("无法读取文件: " + id);
        }
    }

    @Override
    public byte[] getImage(String id) {
        Path filePath = Paths.get(uploadDirectory).resolve(id);
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new ImageException("无法读取文件: " + id);
        }
    }

    @Override
    public byte[] getLowResolutionImage(String id, int newWidth, int newHeight) {
        // 读取原始图像的字节数组
        byte[] imageData = getImage(id);

        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // 解码成 BufferedImage 对象
            BufferedImage originalImage = ImageIO.read(bais);
            // 创建缩略图
            BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            graphics2D.dispose();
            // 将缩略图转换为字节数组
            ImageIO.write(scaledImage, "jpg", baos);
            byte[] lowResImageData = baos.toByteArray();
            return lowResImageData;
        } catch (IOException e) {
            throw new ImageException("无法读取文件: " + id);
        }
        /*
         * sun.awt.image.ToolkitImage and java.awt.image.BufferedImage are in module
         * java.desktop of loader 'bootstrap'
         * 
         * fromChatGPT:
         * 这个警告信息实际上并不是错误，而是Java 9及更高版本引入的模块化系统中的一部分。该警告消息表明 sun.awt.image.ToolkitImage
         * 和 java.awt.image.BufferedImage 类分别位于 java.desktop 模块中，这些类在使用时可能会出现问题。
         * 
         * 在您的代码示例中，使用 ImageIO.read() 方法从字节数组中读取原始图像时，ToolkitImage 和 BufferedImage
         * 都被用到了。这应该是没有问题的，因为 sun.awt.image.ToolkitImage 是 java.awt.Image 的子类，可以被
         * ImageIO.read() 方法识别和处理。
         * 
         * 如果您确实想要消除这个警告消息，可以尝试将以下命令行参数添加到 javac 和 java 命令中：
         * 
         * --add-exports java.desktop/sun.awt=ALL-UNNAMED
         * 这个命令会将 sun.awt
         * 包导出给所有未命名的模块。这样做虽然可以消除警告，但并不是一个推荐的解决方法，因为它会暴露内部API，并且可能会引入安全风险。
         * 
         * 在大多数情况下，由于这个警告实际上不会引起程序故障，因此忽略它是最好的解决方案。
         */

    }

    @Override
    public List<FileInfo> getAllImageFileInfo(int pageNum, int pageSize) {
        try {
            return Files.walk(Paths.get(uploadDirectory))
                    .filter(Files::isRegularFile)
                    .skip((long) (pageNum - 1) * pageSize) // 跳过前 (pageNum-1)*pageSize 个元素
                    .limit(pageSize) // 取出接下来的 pageSize 个元素
                    .map(path -> {
                        try {
                            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                            return new FileInfo(
                                    path.getFileName().toString(),
                                    attr.size(),
                                    new Date(attr.lastModifiedTime().toMillis()));
                        } catch (IOException e) {
                            return new FileInfo("无法获取文件名", 0L, new Date());
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ImageException("无法读取文件列表");
        }
    }

    @Override
    public void deleteImageByName(String filename) {
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
