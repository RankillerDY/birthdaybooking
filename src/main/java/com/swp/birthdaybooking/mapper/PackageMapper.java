package com.swp.birthdaybooking.mapper;

import com.swp.birthdaybooking.Dtos.Request.PackageRequest;
import com.swp.birthdaybooking.entities.Package;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PackageMapper {

    Package rqToEntity(PackageRequest packageRequest);
}
