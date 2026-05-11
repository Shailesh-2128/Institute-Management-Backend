package com.InstituteManagement.InstituteManagement.exam.Service;

import com.InstituteManagement.InstituteManagement.exam.DTO.ExamRequestDTO;
import com.InstituteManagement.InstituteManagement.exam.DTO.ExamResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ExamService {
    ExamResponseDTO createExam(ExamRequestDTO requestDTO);
    List<ExamResponseDTO> getAllExams();
    ExamResponseDTO getExamById(Long id);
    ExamResponseDTO updateExam(Long id, ExamRequestDTO requestDTO);
    void deleteExam(Long id);
    List<ExamResponseDTO> getExamsByCourseId(Long courseId);
    List<ExamResponseDTO> getExamsByDate(LocalDate date);
}
