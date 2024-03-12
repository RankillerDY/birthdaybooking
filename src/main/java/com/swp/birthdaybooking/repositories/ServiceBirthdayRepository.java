package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.ServiceBirthday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ServiceBirthdayRepository extends JpaRepository<ServiceBirthday, Integer> {

    Optional<ServiceBirthday> findByServiceId(Integer integer);

    @Query("""
                  select sb from ServiceBirthday sb
                  inner join ServiceOfPackage sob 
                  on sb.serviceId = sob.serviceBirthday.serviceId
                  where sob.servicePackage.packageId = :packageId
            """)
    List<ServiceBirthday> findAllByPackageId(Integer packageId);

}
