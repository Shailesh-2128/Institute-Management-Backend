package com.InstituteManagement.InstituteManagement.fees.Service;

import com.InstituteManagement.InstituteManagement.fees.DTO.FeesRequestDTO;
import com.InstituteManagement.InstituteManagement.fees.DTO.FeesResponseDTO;
import java.util.List;

public interface FeesService {
    FeesResponseDTO createFees(FeesRequestDTO requestDTO);
    List<FeesResponseDTO> getAllFees();
    FeesResponseDTO getFeesById(Long id);
    FeesResponseDTO updateFees(Long id, FeesRequestDTO requestDTO);
    void deleteFees(Long id);
    FeesResponseDTO getFeesByStudentId(Long studentId);
    List<FeesResponseDTO> getPendingFees();
    List<FeesResponseDTO> getPaidFees();
}
