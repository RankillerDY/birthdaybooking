package com.swp.birthdaybooking.mapper;

import com.swp.birthdaybooking.Dtos.Request.CreateServiceRq;
import com.swp.birthdaybooking.entities.ServiceBirthday;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(source = "name", target = "name")
    ServiceBirthday mapToService(CreateServiceRq createServiceRq);
}
