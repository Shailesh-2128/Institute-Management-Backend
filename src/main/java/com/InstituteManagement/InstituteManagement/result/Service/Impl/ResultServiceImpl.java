package com.InstituteManagement.InstituteManagement.result.Service.Impl;

import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.exam.Entity.Exam;
import com.InstituteManagement.InstituteManagement.exam.Repository.ExamRepository;
import com.InstituteManagement.InstituteManagement.result.DTO.ResultRequestDTO;
import com.InstituteManagement.InstituteManagement.result.DTO.ResultResponseDTO;
import com.InstituteManagement.InstituteManagement.result.Entity.Result;
import com.InstituteManagement.InstituteManagement.result.Repository.ResultRepository;
import com.InstituteManagement.InstituteManagement.result.Service.ResultService;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import com.InstituteManagement.InstituteManagement.student.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResultResponseDTO createResult(ResultRequestDTO requestDTO) {
        Exam exam = examRepository.findById(requestDTO.getExamId())
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found"));
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        
        Result result = Result.builder()
                .exam(exam)
                .student(student)
                .marks(requestDTO.getMarks())
                .grade(requestDTO.getGrade())
                .build();
        return modelMapper.map(resultRepository.save(result), ResultResponseDTO.class);
    }

    @Override
    public List<ResultResponseDTO> getAllResults() {
        return resultRepository.findAll().stream()
                .map(r -> modelMapper.map(r, ResultResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResultResponseDTO getResultById(Long id) {
        Result r = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
        return modelMapper.map(r, ResultResponseDTO.class);
    }

    @Override
    public ResultResponseDTO updateResult(Long id, ResultRequestDTO requestDTO) {
        Result r = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
        if (requestDTO.getMarks() != null) r.setMarks(requestDTO.getMarks());
        if (requestDTO.getGrade() != null) r.setGrade(requestDTO.getGrade());
        return modelMapper.map(resultRepository.save(r), ResultResponseDTO.class);
    }

    @Override
    public void deleteResult(Long id) {
        Result r = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
        resultRepository.delete(r);
    }

    @Override
    public List<ResultResponseDTO> getResultsByStudentId(Long studentId) {
        return resultRepository.findByStudentId(studentId).stream()
                .map(r -> modelMapper.map(r, ResultResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultResponseDTO> getResultsByExamId(Long examId) {
        return resultRepository.findByExamId(examId).stream()
                .map(r -> modelMapper.map(r, ResultResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Object getStudentReport(Long studentId) {
        // Return a summarized map for now
        List<Result> results = resultRepository.findByStudentId(studentId);
        return java.util.Map.of(
            "studentId", studentId,
            "totalExams", results.size(),
            "results", results.stream().map(r -> modelMapper.map(r, ResultResponseDTO.class)).collect(Collectors.toList())
        );
    }
}
