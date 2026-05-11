package com.InstituteManagement.InstituteManagement.student.Repository;

import com.InstituteManagement.InstituteManagement.common.enums.Status;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByStatus(Status status);

    @Query("SELECT s FROM Student s WHERE LOWER(s.user.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.user.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.address) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchStudents(@Param("keyword") String keyword);
}
