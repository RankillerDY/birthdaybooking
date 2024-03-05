package com.swp.birthdaybooking.services;


import com.cloudinary.Cloudinary;
import com.swp.birthdaybooking.Dtos.Request.CreateServiceRq;
import com.swp.birthdaybooking.entities.ServiceBirthday;
import com.swp.birthdaybooking.exception.FileUploadException;
import com.swp.birthdaybooking.mapper.ServiceMapper;
import com.swp.birthdaybooking.repositories.ServiceBirthdayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@Service
@Slf4j
@Transactional
public class ServiceBirthdayService {
    private final ServiceBirthdayRepository serviceRepo;
    private final PackageService packageService;
    private final ServiceMapper serviceMapper;
    private final Cloudinary cloudinary;

    public ServiceBirthdayService(ServiceBirthdayRepository serviceRepo, PackageService packageService, ServiceMapper serviceMapper, Cloudinary cloudinary) {
        this.serviceRepo = serviceRepo;
        this.packageService = packageService;
        this.serviceMapper = serviceMapper;
        this.cloudinary = cloudinary;
    }

    /**
     * P2 usecase 3 : đặt giá cho các dịch vụ của họ.
     */
    public ServiceBirthday setPriceForService(int id, float price) {
        ServiceBirthday serviceBirthday = serviceRepo.findById(id).orElse(null);
        if (serviceBirthday != null) {
            serviceBirthday.setPrice(price);
            var service = serviceRepo.save(serviceBirthday);
            packageService.resetPricePackage(service.getServiceId());
            return service;
        }
        return null;
    }

    public ServiceBirthday createService(CreateServiceRq createServiceRq) {
        var serviceBirthday = serviceMapper.mapToService(createServiceRq);
        return serviceRepo.save(serviceBirthday);
    }

    /**
     * P2 usecase 2 :
     * Người tổ chức sẽ có thể tải ảnh lên
     */
    public Map upload(MultipartFile file) {
        try {
            return cloudinary.uploader().upload(file.getBytes(), Map.of());
        } catch (IOException io) {
            throw new FileUploadException("Failed to upload image", io);
        }
    }
}