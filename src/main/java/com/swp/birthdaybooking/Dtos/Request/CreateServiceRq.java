package com.swp.birthdaybooking.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateServiceRq {
    private String name;
    private boolean status;
    private float price;
    private String description;
    private int locationId;
    private MultipartFile image;

}
