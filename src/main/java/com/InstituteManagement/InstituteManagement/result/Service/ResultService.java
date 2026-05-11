package com.InstituteManagement.InstituteManagement.result.Service;

import com.InstituteManagement.InstituteManagement.result.DTO.ResultRequestDTO;
import com.InstituteManagement.InstituteManagement.result.DTO.ResultResponseDTO;
import java.util.List;

public interface ResultService {
    ResultResponseDTO createResult(ResultRequestDTO requestDTO);
    List<ResultResponseDTO> getAllResults();
    ResultResponseDTO getResultById(Long id);
    ResultResponseDTO updateResult(Long id, ResultRequestDTO requestDTO);
    void deleteResult(Long id);
    List<ResultResponseDTO> getResultsByStudentId(Long studentId);
    List<ResultResponseDTO> getResultsByExamId(Long examId);
    Object getStudentReport(Long studentId);
}
