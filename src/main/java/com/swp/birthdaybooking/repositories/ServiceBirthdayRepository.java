package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.ServiceBirthday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceBirthdayRepository extends JpaRepository<ServiceBirthday, Integer> {

}
