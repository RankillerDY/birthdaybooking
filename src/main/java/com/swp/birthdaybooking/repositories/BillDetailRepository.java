package com.swp.birthdaybooking.repositories;

import com.swp.birthdaybooking.entities.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {
    @Query("SELECT SUM(bd.totalPrice) FROM BillDetail bd WHERE bd.bill.billId = ?1")
    Long getTotalPriceByBillId(Integer billId);
}
