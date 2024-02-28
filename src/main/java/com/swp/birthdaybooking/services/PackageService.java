package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.Dtos.ServiceOfPackageObj;
import com.swp.birthdaybooking.entities.ServiceOfPackage;
import com.swp.birthdaybooking.repositories.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;
    private final Logger logger = LoggerFactory.getLogger(PackageService.class);

    public ResponseEntity<ResponseObject> getParitiesOption() {
        try {
            List<ServiceOfPackageObj> list = new ArrayList<>();
            var parties = packageRepository.getPartiesOption();
            if(parties.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find parties option", parties));
            }
            for (ServiceOfPackage element :parties) {
                ServiceOfPackageObj response = ServiceOfPackageObj.builder()
                        .serviceOfPackageId(element.getServiceOfPackageId())
                        .serviceBirthday(element.getServiceBirthday())
                        .servicePackage(element.getServicePackage())
                        .build();
                list.add(response);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Found parties option", list));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find parties option", null));
        }

    }
}