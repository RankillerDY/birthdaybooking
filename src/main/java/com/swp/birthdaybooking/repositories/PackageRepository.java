package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.Package;
import com.swp.birthdaybooking.entities.ServiceOfPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {

    @Query("""
                select sop from
                Package pack 
                left join ServiceOfPackage sop on pack.packageId = sop.servicePackage.packageId
                left join ServiceBirthday sb on sb.serviceId = sop.serviceBirthday.serviceId
                where pack.status = true and sb.status = true
            """)
    public List<ServiceOfPackage> getPartiesOption();

}
