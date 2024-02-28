package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.entities.Package;
import com.swp.birthdaybooking.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/package")
public class PackageController {
    private final PackageService packageService;

    @GetMapping("/parties")
    public ResponseEntity<ResponseObject> register() {
        return packageService.getParitiesOption();
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getPackages() {
        return  ResponseEntity
                .ok(new ResponseObject("Successful", "Found parties option", packageService.getPackages()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getPackageById(@PathVariable int id) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found parties option", packageService.getPackageById(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createPackage(@RequestBody Package aPackage) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found parties option", packageService.createPackage(aPackage)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updatePackage(@PathVariable int id, @RequestBody Package aPackage) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found parties option", packageService.updatePackage(id, aPackage)));
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found parties option", packageService.upload(file)));
    }
}
