package com.example.app.service;

import com.example.app.dto.EServiceDto;
import com.example.app.entity.EService;
import com.example.app.repository.EServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EServiceService {  // Renamed class for clarity

    private final EServiceRepository eServiceRepository;  // Renamed variable to camel case
    private static final Logger logger = LoggerFactory.getLogger(EServiceService.class);

    public EServiceService(EServiceRepository eServiceRepository) {
        this.eServiceRepository = eServiceRepository;
    }

    public List<EServiceDto> getAllServices() {
        try {
            List<EService> eServices = eServiceRepository.findAll();
            return eServices.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error("Error fetching service entities", ex);
            return new ArrayList<>();  // Return an empty list if there's an error
        }
    }

    public EServiceDto getServiceByCategory(String category) {
        EService eService = eServiceRepository.findByCategory(category);
        if (eService == null) {
            logger.warn("Service not found for category: {}", category);  // Improved logging
            throw new RuntimeException("Service not found for category: " + category);
        }
        return mapToDto(eService);
    }

    private EServiceDto mapToDto(EService eService) {
        List<EServiceDto.FormField> formFields = eService.getFormFields().stream()
                .map(formField -> new EServiceDto.FormField(
                        formField.getFieldName(),
                        formField.getFieldTypes().getFieldTypeName(),
                        formField.isRequired()))
                .collect(Collectors.toList());

        List<EServiceDto.ServiceItemDto> serviceItems = eService.getEServiceItems().stream()
                .map(eServiceItem -> new EServiceDto.ServiceItemDto(eServiceItem.getItem()))
                .collect(Collectors.toList());

        return new EServiceDto(eService.getCategory(), formFields, serviceItems);
    }

}
