package com.example.app.controller;

import com.example.app.dto.ServiceDto;
import com.example.app.service.ServicesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    private final ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping
    public List<ServiceDto> getAllServices() {
        return servicesService.getAllServices();
    }

    @GetMapping("/{category}")
    public ServiceDto getServiceByCategory(@PathVariable String category) {
        return servicesService.getServiceByCategory(category);
    }
}
