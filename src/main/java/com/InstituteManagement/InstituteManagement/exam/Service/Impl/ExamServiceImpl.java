package com.InstituteManagement.InstituteManagement.exam.Service.Impl;

import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.course.Entity.Course;
import com.InstituteManagement.InstituteManagement.course.Repository.CourseRepository;
import com.InstituteManagement.InstituteManagement.exam.DTO.ExamRequestDTO;
import com.InstituteManagement.InstituteManagement.exam.DTO.ExamResponseDTO;
import com.InstituteManagement.InstituteManagement.exam.Entity.Exam;
import com.InstituteManagement.InstituteManagement.exam.Repository.ExamRepository;
import com.InstituteManagement.InstituteManagement.exam.Service.ExamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public ExamResponseDTO createExam(ExamRequestDTO requestDTO) {
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        Exam exam = Exam.builder()
                .course(course)
                .name(requestDTO.getName())
                .date(requestDTO.getDate())
                .build();
        return modelMapper.map(examRepository.save(exam), ExamResponseDTO.class);
    }

    @Override
    public List<ExamResponseDTO> getAllExams() {
        return examRepository.findAll().stream()
                .map(e -> modelMapper.map(e, ExamResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExamResponseDTO getExamById(Long id) {
        Exam e = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found"));
        return modelMapper.map(e, ExamResponseDTO.class);
    }

    @Override
    public ExamResponseDTO updateExam(Long id, ExamRequestDTO requestDTO) {
        Exam e = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found"));
        if (requestDTO.getCourseId() != null) {
            Course course = courseRepository.findById(requestDTO.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
            e.setCourse(course);
        }
        if (requestDTO.getName() != null) e.setName(requestDTO.getName());
        if (requestDTO.getDate() != null) e.setDate(requestDTO.getDate());
        return modelMapper.map(examRepository.save(e), ExamResponseDTO.class);
    }

    @Override
    public void deleteExam(Long id) {
        Exam e = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found"));
        examRepository.delete(e);
    }

    @Override
    public List<ExamResponseDTO> getExamsByCourseId(Long courseId) {
        return examRepository.findByCourseId(courseId).stream()
                .map(e -> modelMapper.map(e, ExamResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResponseDTO> getExamsByDate(LocalDate date) {
        return examRepository.findByDate(date).stream()
                .map(e -> modelMapper.map(e, ExamResponseDTO.class))
                .collect(Collectors.toList());
    }
}
