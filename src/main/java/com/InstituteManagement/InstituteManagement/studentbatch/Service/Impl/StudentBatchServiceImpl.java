package com.InstituteManagement.InstituteManagement.studentbatch.Service.Impl;

import com.InstituteManagement.InstituteManagement.batch.Entity.Batch;
import com.InstituteManagement.InstituteManagement.batch.Repository.BatchRepository;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import com.InstituteManagement.InstituteManagement.student.Repository.StudentRepository;
import com.InstituteManagement.InstituteManagement.studentbatch.DTO.StudentBatchRequestDTO;
import com.InstituteManagement.InstituteManagement.studentbatch.DTO.StudentBatchResponseDTO;
import com.InstituteManagement.InstituteManagement.studentbatch.Entity.StudentBatch;
import com.InstituteManagement.InstituteManagement.studentbatch.Repository.StudentBatchRepository;
import com.InstituteManagement.InstituteManagement.studentbatch.Service.StudentBatchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentBatchServiceImpl implements StudentBatchService {

    private final StudentBatchRepository studentBatchRepository;
    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentBatchResponseDTO createStudentBatch(StudentBatchRequestDTO requestDTO) {
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Batch batch = batchRepository.findById(requestDTO.getBatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));

        StudentBatch studentBatch = StudentBatch.builder()
                .student(student)
                .batch(batch)
                .build();

        return modelMapper.map(studentBatchRepository.save(studentBatch), StudentBatchResponseDTO.class);
    }

    @Override
    public List<StudentBatchResponseDTO> getAllStudentBatches() {
        return studentBatchRepository.findAll().stream()
                .map(sb -> modelMapper.map(sb, StudentBatchResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentBatchResponseDTO getStudentBatchById(Long id) {
        StudentBatch sb = studentBatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudentBatch not found"));
        return modelMapper.map(sb, StudentBatchResponseDTO.class);
    }

    @Override
    public void deleteStudentBatch(Long id) {
        StudentBatch sb = studentBatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudentBatch not found"));
        studentBatchRepository.delete(sb);
    }

    @Override
    public List<StudentBatchResponseDTO> getStudentBatchesByStudentId(Long studentId) {
        return studentBatchRepository.findByStudentId(studentId).stream()
                .map(sb -> modelMapper.map(sb, StudentBatchResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentBatchResponseDTO> getStudentBatchesByBatchId(Long batchId) {
        return studentBatchRepository.findByBatchId(batchId).stream()
                .map(sb -> modelMapper.map(sb, StudentBatchResponseDTO.class))
                .collect(Collectors.toList());
    }
}
