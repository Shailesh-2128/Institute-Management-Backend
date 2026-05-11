package com.InstituteManagement.InstituteManagement.staff.Service;

import com.InstituteManagement.InstituteManagement.staff.DTO.StaffRequestDTO;
import com.InstituteManagement.InstituteManagement.staff.DTO.StaffResponseDTO;

import java.util.List;

public interface StaffService {
    StaffResponseDTO createStaff(StaffRequestDTO requestDTO);
    List<StaffResponseDTO> getAllStaff();
    StaffResponseDTO getStaffById(Long id);
    StaffResponseDTO updateStaff(Long id, StaffRequestDTO requestDTO);
    void deleteStaff(Long id);
    
    StaffResponseDTO getCurrentStaff();
    StaffResponseDTO updateCurrentStaff(StaffRequestDTO requestDTO);
    
    List<StaffResponseDTO> searchStaff(String keyword);
    List<StaffResponseDTO> getStaffByDesignation(String designation);
    List<StaffResponseDTO> getActiveStaff();
}
