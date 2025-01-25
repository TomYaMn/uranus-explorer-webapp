package com.example.app.service;

import com.example.app.dto.ServiceDto;
import com.example.app.entity.ServiceEntity;
import com.example.app.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    private final ServiceRepository serviceRepository;
    private static final Logger logger = LoggerFactory.getLogger(ServicesService.class);

    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDto> getAllServices() {
        List<ServiceDto> list = new ArrayList<>();
        try {
            List<ServiceEntity> serviceEntities = serviceRepository.findAll();

            return serviceEntities.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error("Error fetching service entities", ex);
            return list;
        }
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
                serviceEntity.getServiceItems()
                        .stream()
                        .map(item -> new ServiceDto.ServiceItemDto(item.getItem())) // Map ServiceItem to ServiceItemDto
                        .collect(Collectors.toList())
        );
    }

}
