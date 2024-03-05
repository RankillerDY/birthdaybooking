package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.ServiceBirthdayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/birthdayService")
public class BirthDayServiceController {
    private final ServiceBirthdayService serviceBirthdayService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id) {
        return serviceBirthdayService.getServiceDetails(id);
    }


}
