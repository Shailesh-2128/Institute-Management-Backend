package com.InstituteManagement.InstituteManagement.course.Repository;

import com.InstituteManagement.InstituteManagement.course.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
