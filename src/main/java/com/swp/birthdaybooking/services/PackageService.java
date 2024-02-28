package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.repositories.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;

    public ResponseEntity<ResponseObject> getParitiesOption() {
        try {
            var parties = packageRepository.getPartiesOption();
            if(parties.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find parties option", parties));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Found parties option", parties));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find parties option", null));
        }

    }
}