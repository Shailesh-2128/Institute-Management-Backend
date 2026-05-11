package com.InstituteManagement.InstituteManagement.fees.Service.Impl;

import com.InstituteManagement.InstituteManagement.common.enums.FeeStatus;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.fees.DTO.FeesRequestDTO;
import com.InstituteManagement.InstituteManagement.fees.DTO.FeesResponseDTO;
import com.InstituteManagement.InstituteManagement.fees.Entity.Fees;
import com.InstituteManagement.InstituteManagement.fees.Repository.FeesRepository;
import com.InstituteManagement.InstituteManagement.fees.Service.FeesService;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import com.InstituteManagement.InstituteManagement.student.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeesServiceImpl implements FeesService {

    private final FeesRepository feesRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public FeesResponseDTO createFees(FeesRequestDTO requestDTO) {
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Fees fees = Fees.builder()
                .student(student)
                .totalAmount(requestDTO.getTotalAmount())
                .paidAmount(requestDTO.getPaidAmount())
                .dueAmount(requestDTO.getDueAmount())
                .status(requestDTO.getStatus())
                .build();
        return modelMapper.map(feesRepository.save(fees), FeesResponseDTO.class);
    }

    @Override
    public List<FeesResponseDTO> getAllFees() {
        return feesRepository.findAll().stream()
                .map(f -> modelMapper.map(f, FeesResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FeesResponseDTO getFeesById(Long id) {
        Fees f = feesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fees not found"));
        return modelMapper.map(f, FeesResponseDTO.class);
    }

    @Override
    public FeesResponseDTO updateFees(Long id, FeesRequestDTO requestDTO) {
        Fees f = feesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fees not found"));
        if (requestDTO.getTotalAmount() != null) f.setTotalAmount(requestDTO.getTotalAmount());
        if (requestDTO.getPaidAmount() != null) f.setPaidAmount(requestDTO.getPaidAmount());
        if (requestDTO.getDueAmount() != null) f.setDueAmount(requestDTO.getDueAmount());
        if (requestDTO.getStatus() != null) f.setStatus(requestDTO.getStatus());
        return modelMapper.map(feesRepository.save(f), FeesResponseDTO.class);
    }

    @Override
    public void deleteFees(Long id) {
        Fees f = feesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fees not found"));
        feesRepository.delete(f);
    }

    @Override
    public FeesResponseDTO getFeesByStudentId(Long studentId) {
        Fees f = feesRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Fees not found for student"));
        return modelMapper.map(f, FeesResponseDTO.class);
    }

    @Override
    public List<FeesResponseDTO> getPendingFees() {
        return feesRepository.findByStatus(FeeStatus.PENDING).stream()
                .map(f -> modelMapper.map(f, FeesResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FeesResponseDTO> getPaidFees() {
        return feesRepository.findByStatus(FeeStatus.PAID).stream()
                .map(f -> modelMapper.map(f, FeesResponseDTO.class))
                .collect(Collectors.toList());
    }
}
