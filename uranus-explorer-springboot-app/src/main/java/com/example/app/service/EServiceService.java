package com.example.app.service;

import com.example.app.dto.EServiceDto;
import com.example.app.entity.EService;
import com.example.app.entity.FormField;
import com.example.app.entity.FormFieldTooltip;
import com.example.app.repository.EServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EServiceService {

    private final EServiceRepository eServiceRepository;
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
            logger.error("Error fetching all services", ex);
            return new ArrayList<>();
        }
    }

    public EServiceDto getServiceByCategory(String eServiceName) {
        try {
            EService eService = eServiceRepository.findByEServiceName(eServiceName);
            if (eService == null) {
                logger.warn("Service not found for category: {}", eServiceName);
                throw new ServiceNotFoundException("Service not found for category: " + eServiceName);
            }
            return mapToDto(eService);
        } catch (Exception ex) {
            logger.error("Error fetching service by category: {}", eServiceName, ex);
            throw ex; // Rethrow after logging
        }
    }

    public EServiceDto mapToDto(EService eService) {
        if (eService == null) {
            return null;
        }

        List<EServiceDto.FormField> formFields = mapFormFields(eService);
        List<EServiceDto.ServiceItemDto> serviceItems = mapServiceItems(eService);

        return new EServiceDto(
                eService.geteServiceName(),
                eService.getId(), // Assuming serviceId is the EService's ID
                formFields,
                serviceItems
        );
    }

    private List<EServiceDto.FormField> mapFormFields(EService eService) {
        return Optional.ofNullable(eService.getFormFields())
                .orElse(Collections.emptyList())
                .stream()
                .map(formField -> new EServiceDto.FormField(
                        formField.getFieldName(),
                        formField.isRequired(),
                        Optional.ofNullable(formField.getFieldType())
                                .map(fieldType -> fieldType.getId())
                                .orElse(null),
                        getTooltip(formField),  // Mapping tooltip from the separate table
                        formField.geteService().getId(),
                        formField.isStatus(),
                        formField.getFieldOptions() // Assuming it's stored as a JSON string
                ))
                .collect(Collectors.toList());
    }

    private EServiceDto.FormFieldTooltipDto getTooltip(FormField formField) {
        // Check if the FormField has a related tooltip (from the form_field_tooltip table)
        return Optional.ofNullable(formField.getTooltip())  // Assuming there's a getTooltip method in FormField
                .map(tooltip -> new EServiceDto.FormFieldTooltipDto(tooltip.getTooltipText()))
                .orElse(null);
    }

    private List<EServiceDto.ServiceItemDto> mapServiceItems(EService eService) {
        return Optional.ofNullable(eService.getEServiceItems())
                .orElse(Collections.emptyList())
                .stream()
                .map(serviceItem -> new EServiceDto.ServiceItemDto(serviceItem.getEServiceItem()))
                .collect(Collectors.toList());
    }

    public static class ServiceNotFoundException extends RuntimeException {
        public ServiceNotFoundException(String message) {
            super(message);
        }
    }
}
