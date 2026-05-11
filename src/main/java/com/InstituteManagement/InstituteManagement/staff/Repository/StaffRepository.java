package com.InstituteManagement.InstituteManagement.staff.Repository;

import com.InstituteManagement.InstituteManagement.common.enums.Status;
import com.InstituteManagement.InstituteManagement.staff.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findByDesignationIgnoreCase(String designation);

    List<Staff> findByStatus(Status status);

    Optional<Staff> findByUserId(Long userId);

    @Query("SELECT s FROM Staff s WHERE LOWER(s.designation) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.user.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.user.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Staff> searchStaff(@Param("keyword") String keyword);
}
