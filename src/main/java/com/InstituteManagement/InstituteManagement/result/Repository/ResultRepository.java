package com.InstituteManagement.InstituteManagement.result.Repository;

import com.InstituteManagement.InstituteManagement.result.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudentId(Long studentId);
    List<Result> findByExamId(Long examId);
}
