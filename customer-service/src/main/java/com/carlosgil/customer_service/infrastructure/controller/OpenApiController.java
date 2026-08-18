package com.carlosgil.customer_service.infrastructure.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenApiController {

    private final Resource openApi;

    public OpenApiController(ResourceLoader resourceLoader) {
        this.openApi = resourceLoader.getResource("classpath:/openapi.yml");
    }

    @GetMapping(value = "/openapi.yml", produces = "application/yaml")
    public Resource openApi() {
        return openApi;
    }
}