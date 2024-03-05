package com.swp.birthdaybooking.mapper;

import com.swp.birthdaybooking.Dtos.Request.GuestRq;
import com.swp.birthdaybooking.entities.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    @Mapping(source = "id", target = "guestId")
    Guest mapToEntity(GuestRq guest);
}
