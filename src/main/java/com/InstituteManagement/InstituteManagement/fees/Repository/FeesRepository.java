package com.InstituteManagement.InstituteManagement.fees.Repository;

import com.InstituteManagement.InstituteManagement.common.enums.FeeStatus;
import com.InstituteManagement.InstituteManagement.fees.Entity.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FeesRepository extends JpaRepository<Fees, Long> {
    Optional<Fees> findByStudentId(Long studentId);
    List<Fees> findByStatus(FeeStatus status);
}
