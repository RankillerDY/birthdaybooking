package com.swp.birthdaybooking.Dtos.Request;

import com.swp.birthdaybooking.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageRequest {
    private Integer packageId;
    private String name;
    private boolean status;
    private float price;
    private String description;
}
