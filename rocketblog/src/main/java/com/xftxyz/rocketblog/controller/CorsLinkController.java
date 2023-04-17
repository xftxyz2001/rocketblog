package com.xftxyz.rocketblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 跨域链接
 */
@RestController
public class CorsLinkController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取链接内容
     * 
     * @param url 链接地址
     * @return 链接内容
     */
    @GetMapping("/link")
    public ResponseEntity<String> getLink(@RequestParam String url) {
        return restTemplate.getForEntity(url, String.class);
    }

}
