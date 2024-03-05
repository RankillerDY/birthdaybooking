package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.PackageRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.Dtos.ServiceOfPackageObj;
import com.swp.birthdaybooking.entities.Package;
import com.swp.birthdaybooking.entities.ServiceOfPackage;
import com.swp.birthdaybooking.mapper.PackageMapper;
import com.swp.birthdaybooking.repositories.PackageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService extends BaseService<Package, Integer> {
    private final PackageRepository packageRepository;
    private final PackageMapper packageMapper;

    public PackageService(PackageRepository packageRepository, PackageMapper packageMapper) {
        super(packageRepository);
        this.packageRepository = packageRepository;
        this.packageMapper = packageMapper;
    }

    public void resetPricePackage(int serviceId) {
        var packageOptional = packageRepository.findAllByServiceId(serviceId);
        packageOptional.ifPresent(packages -> {
            packages.forEach(pack -> {
                var totalPrice = packageRepository.getTotalPricesServiceByPackageId(pack.getPackageId());
                pack.setPrice(totalPrice);
                packageRepository.save(pack);
            });
        });
    }

    public ResponseEntity<ResponseObject> getParitiesOption() {
        try {
            List<ServiceOfPackageObj> list = new ArrayList<>();
            var parties = packageRepository.getPartiesOption();
            if (parties.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find parties option", parties));
            }
            for (ServiceOfPackage element : parties) {
                ServiceOfPackageObj response = ServiceOfPackageObj.builder()
                        .serviceOfPackageId(element.getServiceOfPackageId())
                        .serviceBirthday(element.getServiceBirthday())
                        .servicePackage(element.getServicePackage())
                        .build();
                list.add(response);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Found parties option", list));
        } catch (Exception e) {
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

    public Package getPackageById(int id) {
        return getById(id);
    }

    public Package createPackage(PackageRequest packageRequest) {
        return packageRepository.save(packageMapper.mapToPackage(packageRequest));
    }

    public Package updatePackage(int id, PackageRequest packageRequest) {
        var result = getById(id);
        packageMapper.mapToPackage(packageRequest, result);
        return packageRepository.save(result);
    }

    public void deletePackage(int id) {
        isIdExist(id).deleteById(id);
    }


}