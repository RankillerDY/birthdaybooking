package com.swp.birthdaybooking.Dtos.Request;

import com.swp.birthdaybooking.entities.Cart;
import com.swp.birthdaybooking.entities.Location;
import com.swp.birthdaybooking.entities.ServiceOfPackage;

import java.util.List;

public class PackageRequest {
    private int packageId;
    private Location location;
    private List<Cart> cartList;
    private List<ServiceOfPackage> serviceOfPackageList;
    private String name;
    private boolean status;
    private float price;
    private String description;
}
