package com.InstituteManagement.InstituteManagement.payment.Repository;

import com.InstituteManagement.InstituteManagement.common.enums.PaymentMode;
import com.InstituteManagement.InstituteManagement.payment.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentId(Long studentId);
    List<Payment> findByMode(PaymentMode mode);

    @Query("SELECT p FROM Payment p WHERE CAST(p.date AS DATE) = :date")
    List<Payment> findByDate(@Param("date") LocalDate date);
}
