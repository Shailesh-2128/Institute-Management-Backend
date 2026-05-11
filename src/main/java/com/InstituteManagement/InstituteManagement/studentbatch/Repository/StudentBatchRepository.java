package com.InstituteManagement.InstituteManagement.studentbatch.Repository;

import com.InstituteManagement.InstituteManagement.studentbatch.Entity.StudentBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentBatchRepository extends JpaRepository<StudentBatch, Long> {
    List<StudentBatch> findByStudentId(Long studentId);
    List<StudentBatch> findByBatchId(Long batchId);
}
