package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.PackageRequest;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/package")
public class PackageController {
    private final PackageService packageService;

    @GetMapping("/parties")
    public ResponseEntity<ResponseObject> getParties() {
        return packageService.getParitiesOption();
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getPackages() {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found packages",
                        packageService.getPackages()));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getPackagesAndServices() {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found packages",
                        packageService.getPackagesAndServices()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getPackageById(@PathVariable int id) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found packages",
                        packageService.getPackageById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('host:create')")
    public ResponseEntity<ResponseObject> createPackage(@RequestBody PackageRequest packageRequest) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found packages",
                        packageService.createPackage(packageRequest)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('host:update')")
    public ResponseEntity<ResponseObject> updatePackage(@PathVariable int id, @RequestBody PackageRequest packageRequest) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Found packages",
                        packageService.updatePackage(id, packageRequest)));
    }

}
