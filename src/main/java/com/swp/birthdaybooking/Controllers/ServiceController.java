package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.CreateServiceRq;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.ServiceBirthdayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
    private final ServiceBirthdayService serviceBirthdayService;

    public ServiceController(ServiceBirthdayService serviceBirthdayService) {
        this.serviceBirthdayService = serviceBirthdayService;
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createService(@RequestBody CreateServiceRq createServiceRq) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Service created",
                        serviceBirthdayService.createService(createServiceRq)));
    }
}
