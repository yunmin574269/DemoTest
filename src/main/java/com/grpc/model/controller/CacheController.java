package com.grpc.model.controller;

import com.grpc.model.server.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class CacheController {

    @Autowired
    private CaffeineService caffeineService;

    @GetMapping("/cache-izuul/{key}")
    public String cacheIZUUL(@PathVariable String key) {

        return caffeineService.cacheIZUUL(key);
    }

    @GetMapping("/cache-put-izuul/{key}")
    public String cachePutIZUUL(@PathVariable String key) {

        return caffeineService.cachePutIZUUL(key);
    }
}
