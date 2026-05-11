package com.InstituteManagement.InstituteManagement.studentbatch.Service;

import com.InstituteManagement.InstituteManagement.studentbatch.DTO.StudentBatchRequestDTO;
import com.InstituteManagement.InstituteManagement.studentbatch.DTO.StudentBatchResponseDTO;
import java.util.List;

public interface StudentBatchService {
    StudentBatchResponseDTO createStudentBatch(StudentBatchRequestDTO requestDTO);
    List<StudentBatchResponseDTO> getAllStudentBatches();
    StudentBatchResponseDTO getStudentBatchById(Long id);
    void deleteStudentBatch(Long id);
    List<StudentBatchResponseDTO> getStudentBatchesByStudentId(Long studentId);
    List<StudentBatchResponseDTO> getStudentBatchesByBatchId(Long batchId);
}
