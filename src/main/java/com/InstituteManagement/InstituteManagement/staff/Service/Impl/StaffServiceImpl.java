package com.InstituteManagement.InstituteManagement.staff.Service.Impl;

import com.InstituteManagement.InstituteManagement.User.Entity.User;
import com.InstituteManagement.InstituteManagement.User.Repository.UserRepository;
import com.InstituteManagement.InstituteManagement.common.enums.Status;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.staff.DTO.StaffRequestDTO;
import com.InstituteManagement.InstituteManagement.staff.DTO.StaffResponseDTO;
import com.InstituteManagement.InstituteManagement.staff.Entity.Staff;
import com.InstituteManagement.InstituteManagement.staff.Repository.StaffRepository;
import com.InstituteManagement.InstituteManagement.staff.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public StaffResponseDTO createStaff(StaffRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDTO.getUserId()));

        Staff staff = Staff.builder()
                .user(user)
                .designation(requestDTO.getDesignation())
                .salary(requestDTO.getSalary())
                .status(Status.ACTIVE)
                .build();

        return modelMapper.map(staffRepository.save(staff), StaffResponseDTO.class);
    }

    @Override
    public List<StaffResponseDTO> getAllStaff() {
        return staffRepository.findAll().stream()
                .map(staff -> modelMapper.map(staff, StaffResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StaffResponseDTO getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        return modelMapper.map(staff, StaffResponseDTO.class);
    }

    @Override
    public StaffResponseDTO updateStaff(Long id, StaffRequestDTO requestDTO) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));

        if(requestDTO.getUserId() != null) {
            User user = userRepository.findById(requestDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDTO.getUserId()));
            staff.setUser(user);
        }
        
        if(requestDTO.getDesignation() != null) {
            staff.setDesignation(requestDTO.getDesignation());
        }
        if(requestDTO.getSalary() != null) {
            staff.setSalary(requestDTO.getSalary());
        }

        return modelMapper.map(staffRepository.save(staff), StaffResponseDTO.class);
    }

    @Override
    public void deleteStaff(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        staffRepository.delete(staff);
    }

    @Override
    public StaffResponseDTO getCurrentStaff() {
        // Mock current user ID = 1L
        Staff staff = staffRepository.findByUserId(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Staff profile not found for current user"));
        return modelMapper.map(staff, StaffResponseDTO.class);
    }

    @Override
    public StaffResponseDTO updateCurrentStaff(StaffRequestDTO requestDTO) {
        // Mock current user ID = 1L
        Staff staff = staffRepository.findByUserId(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Staff profile not found for current user"));
        
        if(requestDTO.getDesignation() != null) {
            staff.setDesignation(requestDTO.getDesignation());
        }
        if(requestDTO.getSalary() != null) {
            staff.setSalary(requestDTO.getSalary());
        }

        return modelMapper.map(staffRepository.save(staff), StaffResponseDTO.class);
    }

    @Override
    public List<StaffResponseDTO> searchStaff(String keyword) {
        return staffRepository.searchStaff(keyword).stream()
                .map(staff -> modelMapper.map(staff, StaffResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffResponseDTO> getStaffByDesignation(String designation) {
        return staffRepository.findByDesignationIgnoreCase(designation).stream()
                .map(staff -> modelMapper.map(staff, StaffResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffResponseDTO> getActiveStaff() {
        return staffRepository.findByStatus(Status.ACTIVE).stream()
                .map(staff -> modelMapper.map(staff, StaffResponseDTO.class))
                .collect(Collectors.toList());
    }
}
