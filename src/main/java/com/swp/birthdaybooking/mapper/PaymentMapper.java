package com.swp.birthdaybooking.mapper;

import com.swp.birthdaybooking.Dtos.Request.CreateCartRq;
import com.swp.birthdaybooking.Dtos.Request.CreatePaymentRq;
import com.swp.birthdaybooking.entities.*;
import com.swp.birthdaybooking.entities.Package;
import com.swp.birthdaybooking.exception.NotFoundException;
import com.swp.birthdaybooking.repositories.BillRepository;
import com.swp.birthdaybooking.repositories.PackageRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PaymentMapper {
    @Autowired
    private BillRepository billRepository;

    @Mapping(source = "billId", target = "bill", qualifiedByName = "idToBill")
    public abstract Payment mapToPayment(CreatePaymentRq paymentRq);

    @Named("idToBill")
    public Bill idToBill(Integer id) {
        if (id == null) {
            return null;
        }
        if (!billRepository.existsById(id)) {
            throw new NotFoundException("Bill with id " + id + " not found");
        }
        return new Bill(id);
    }

}
