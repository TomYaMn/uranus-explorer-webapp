package com.example.app.service;

import com.example.app.dto.ServiceDto;
import com.example.app.entity.ServiceEntity;
import com.example.app.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    private final ServiceRepository serviceRepository;

    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDto> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ServiceDto getServiceByCategory(String category) {
        ServiceEntity serviceEntity = serviceRepository.findByCategory(category);
        if (serviceEntity == null) {
            throw new RuntimeException("Service not found for category: " + category);
        }
        return mapToDto(serviceEntity);
    }

    private ServiceDto mapToDto(ServiceEntity serviceEntity) {
        return new ServiceDto(
                serviceEntity.getCategory(),
                serviceEntity.getFormFields()
                        .stream()
                        .map(field -> new ServiceDto.FormField(
                                field.getFieldName(),
                                field.getFieldType(),
                                field.isRequired()))
                        .collect(Collectors.toList()),
                serviceEntity.getFormTitle()
        );
    }
}
