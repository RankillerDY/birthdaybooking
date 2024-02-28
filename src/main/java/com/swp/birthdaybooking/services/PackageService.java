package com.swp.birthdaybooking.services;

import com.cloudinary.Cloudinary;
import com.swp.birthdaybooking.Dtos.Request.PackageRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.Dtos.ServiceOfPackageObj;
import com.swp.birthdaybooking.entities.Package;
import com.swp.birthdaybooking.entities.ServiceOfPackage;
import com.swp.birthdaybooking.exception.FileUploadException;
import com.swp.birthdaybooking.mapper.PackageMapper;
import com.swp.birthdaybooking.repositories.PackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PackageService {
    private final PackageRepository packageRepository;
    private final Cloudinary cloudinary;
    private final PackageMapper packageMapper;

    public PackageService(PackageRepository packageRepository, Cloudinary cloudinary, PackageMapper packageMapper) {
        this.packageRepository = packageRepository;
        this.cloudinary = cloudinary;
        this.packageMapper = packageMapper;
    }

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

    /**
     * P2 usecase 1 :
     * Tạo và quản lý danh sách bữa tiệc của mình bằng cách cung cấp các chi tiết như thông tin địa điểm,
     * ngày tổ chức, chủ đề bữa tiệc và các tùy chọn gói.
     */
    public List<Package> getPackages() {
        return packageRepository.findAll();
    }

    public Optional<Package> getPackageById(int id) {
        return packageRepository.findById(id);
    }

    public Package createPackage(PackageRequest packageRequest) {
        return packageRepository.save(packageMapper.rqToEntity(packageRequest));
    }

    public Package updatePackage(int id, PackageRequest packageRequest) {
        var packageOptional = packageRepository.findById(id);
        if (packageOptional.isEmpty()) {
            return null;
        }
        packageRequest.setPackageId(id);
        return packageRepository.save(packageMapper.rqToEntity(packageRequest));
    }

    public void deletePackage(int id) {
        var packageOptional = packageRepository.findById(id);
        if (packageOptional.isPresent()) {
            packageRepository.deleteById(id);
        }
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