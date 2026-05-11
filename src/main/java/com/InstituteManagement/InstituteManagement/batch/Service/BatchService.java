package com.InstituteManagement.InstituteManagement.batch.Service;

import com.InstituteManagement.InstituteManagement.batch.DTO.BatchRequestDTO;
import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;

import java.util.List;
import java.util.Map;

public interface BatchService {
    BatchResponseDTO createBatch(BatchRequestDTO requestDTO);
    List<BatchResponseDTO> getAllBatches();
    BatchResponseDTO getBatchById(Long id);
    BatchResponseDTO updateBatch(Long id, BatchRequestDTO requestDTO);
    void deleteBatch(Long id);
    
    List<BatchResponseDTO> searchBatches(String keyword);
    List<BatchResponseDTO> getBatchesByCourse(Long courseId);
    List<BatchResponseDTO> getBatchesByStaff(Long staffId);
    
    List<StudentResponseDTO> getStudentsInBatch(Long id);
    void addStudentToBatch(Long batchId, Long studentId);
    void removeStudentFromBatch(Long batchId, Long studentId);
    
    long getBatchCount();
    Map<String, Object> getBatchStats(Long id);
}
