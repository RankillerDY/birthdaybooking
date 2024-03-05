package com.swp.birthdaybooking.services;


import com.cloudinary.Cloudinary;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.entities.ServiceBirthday;
import com.swp.birthdaybooking.repositories.LocationRepository;
import com.swp.birthdaybooking.repositories.ServiceBirthdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;



@RequiredArgsConstructor
@Service
public class ServiceBirthdayService {
    private final ServiceBirthdayRepository repository;

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

    public ResponseEntity<ResponseObject> getServiceDetails(int id) {
        try {
            ServiceBirthday serviceBirthday = repository.findById(id).orElse(null);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Success", "Retrieve service", serviceBirthday));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Failed", "Fail to retrieve service", null));
        }
    }

    private ServiceBirthday generateNewService(ServiceBirthday serviceBirthday) {
        ServiceBirthday serviceBirthday1 = ServiceBirthday.builder()
                .name(serviceBirthday.getName())
                .description(serviceBirthday.getDescription())
                .serviceId(serviceBirthday.getServiceId())
                .serviceDetailList(serviceBirthday.getServiceDetailList())
                .feedbackList(serviceBirthday.getFeedbackList())
                .picture(serviceBirthday.getPicture())
                .price(serviceBirthday.getPrice())
                .status(serviceBirthday.isStatus())
                .location(serviceBirthday.getLocation())
                .build();
        return serviceBirthday1;
    }
}