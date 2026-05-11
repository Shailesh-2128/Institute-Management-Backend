package com.InstituteManagement.InstituteManagement.exam.Repository;

import com.InstituteManagement.InstituteManagement.exam.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCourseId(Long courseId);
    List<Exam> findByDate(LocalDate date);
}
