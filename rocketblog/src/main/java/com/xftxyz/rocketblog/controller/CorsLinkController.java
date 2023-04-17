package com.xftxyz.rocketblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CorsLinkController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/link")
    public ResponseEntity<String> getLink(@RequestParam String url) {
        return restTemplate.getForEntity(url, String.class);
    }

}
