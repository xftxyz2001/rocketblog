package com.xftxyz.rocketblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CorsLinkController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/l/{link}")
    public ResponseEntity<String> getLink(@PathVariable("link") String link) {
        return restTemplate.getForEntity(link, String.class);
    }

}
