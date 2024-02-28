package com.swp.birthdaybooking.services;


import com.cloudinary.Cloudinary;
import com.swp.birthdaybooking.entities.ServiceBirthday;
import com.swp.birthdaybooking.repositories.ServiceBirthdayRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
public class ServiceBirthdayService {
    private final ServiceBirthdayRepository repository;

    public ServiceBirthdayService(ServiceBirthdayRepository repository) {
        this.repository = repository;
    }

    /**
     * P2 usecase 3 : đặt giá cho các dịch vụ của họ.
     */
    public ServiceBirthday setPriceForService(int id, float price) {
        ServiceBirthday serviceBirthday = repository.findById(id).orElse(null);
        if (serviceBirthday != null) {
            serviceBirthday.setPrice(price);
            return repository.save(serviceBirthday);
        }
        return null;
    }
}