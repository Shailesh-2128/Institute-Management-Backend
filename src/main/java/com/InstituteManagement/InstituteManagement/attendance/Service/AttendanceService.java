package com.InstituteManagement.InstituteManagement.attendance.Service;

import com.InstituteManagement.InstituteManagement.attendance.DTO.AttendanceRequestDTO;
import com.InstituteManagement.InstituteManagement.attendance.DTO.AttendanceResponseDTO;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    AttendanceResponseDTO createAttendance(AttendanceRequestDTO requestDTO);
    List<AttendanceResponseDTO> getAllAttendance();
    AttendanceResponseDTO getAttendanceById(Long id);
    AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO requestDTO);
    void deleteAttendance(Long id);
    List<AttendanceResponseDTO> getAttendanceByStudentId(Long studentId);
    Double getAttendancePercentageByStudentId(Long studentId);
    List<AttendanceResponseDTO> getAttendanceByBatchId(Long batchId);
    List<AttendanceResponseDTO> getAttendanceByDate(LocalDate date);
}
