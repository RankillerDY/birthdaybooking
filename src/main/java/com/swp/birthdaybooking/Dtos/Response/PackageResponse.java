package com.swp.birthdaybooking.Dtos.Response;

import com.swp.birthdaybooking.entities.Location;
import com.swp.birthdaybooking.entities.ServiceBirthday;

import java.util.List;

public record PackageResponse(
        Integer packageId,
        String name,
        Boolean status,
        String description,
        Float price,
        Location location,
        List<ServiceBirthday> services
) {
}
