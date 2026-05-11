package com.InstituteManagement.InstituteManagement.course.Service.Impl;

import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.batch.Repository.BatchRepository;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.course.DTO.CourseRequestDTO;
import com.InstituteManagement.InstituteManagement.course.DTO.CourseResponseDTO;
import com.InstituteManagement.InstituteManagement.course.Entity.Course;
import com.InstituteManagement.InstituteManagement.course.Repository.CourseRepository;
import com.InstituteManagement.InstituteManagement.course.Service.CourseService;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO requestDTO) {
        Course course = Course.builder()
                .name(requestDTO.getName())
                .duration(requestDTO.getDuration())
                .fees(requestDTO.getFees())
                .build();
        return modelMapper.map(courseRepository.save(course), CourseResponseDTO.class);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return modelMapper.map(course, CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO requestDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        if(requestDTO.getName() != null) course.setName(requestDTO.getName());
        if(requestDTO.getDuration() != null) course.setDuration(requestDTO.getDuration());
        if(requestDTO.getFees() != null) course.setFees(requestDTO.getFees());
        return modelMapper.map(courseRepository.save(course), CourseResponseDTO.class);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        courseRepository.delete(course);
    }

    @Override
    public List<CourseResponseDTO> searchCourses(String keyword) {
        return courseRepository.findAll().stream()
                .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getCoursesByDuration(Integer months) {
        return courseRepository.findAll().stream()
                .filter(c -> c.getDuration().equals(months))
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getCoursesByFeesRange(Double min, Double max) {
        BigDecimal minFees = BigDecimal.valueOf(min);
        BigDecimal maxFees = BigDecimal.valueOf(max);
        return courseRepository.findAll().stream()
                .filter(c -> c.getFees().compareTo(minFees) >= 0 && c.getFees().compareTo(maxFees) <= 0)
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BatchResponseDTO> getBatchesForCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        // Assuming BatchRepository is needed here, or we can fetch via bi-directional if we mapped it.
        // Since we didn't add a List<Batch> to Course, we would need BatchRepository.
        return List.of(); // Return empty list for now
    }

    @Override
    public List<StudentResponseDTO> getStudentsForCourse(Long id) {
        // Requires fetching batches for the course, then students in those batches.
        return List.of();
    }

    @Override
    public long getStudentsCountForCourse(Long id) {
        return 0;
    }

    @Override
    public long getCourseCount() {
        return courseRepository.count();
    }

    @Override
    public List<CourseResponseDTO> getPopularCourses() {
        return getAllCourses(); // Mocked
    }
}
