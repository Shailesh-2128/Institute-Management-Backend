package com.InstituteManagement.InstituteManagement.attendance.Service.Impl;

import com.InstituteManagement.InstituteManagement.attendance.DTO.AttendanceRequestDTO;
import com.InstituteManagement.InstituteManagement.attendance.DTO.AttendanceResponseDTO;
import com.InstituteManagement.InstituteManagement.attendance.Entity.Attendance;
import com.InstituteManagement.InstituteManagement.attendance.Repository.AttendanceRepository;
import com.InstituteManagement.InstituteManagement.attendance.Service.AttendanceService;
import com.InstituteManagement.InstituteManagement.common.enums.AttendanceStatus;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import com.InstituteManagement.InstituteManagement.student.Repository.StudentRepository;
import com.InstituteManagement.InstituteManagement.studentbatch.Entity.StudentBatch;
import com.InstituteManagement.InstituteManagement.studentbatch.Repository.StudentBatchRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final StudentBatchRepository studentBatchRepository;
    private final ModelMapper modelMapper;

    @Override
    public AttendanceResponseDTO createAttendance(AttendanceRequestDTO requestDTO) {
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Attendance attendance = Attendance.builder()
                .student(student)
                .date(requestDTO.getDate())
                .status(requestDTO.getStatus())
                .build();
        return modelMapper.map(attendanceRepository.save(attendance), AttendanceResponseDTO.class);
    }

    @Override
    public List<AttendanceResponseDTO> getAllAttendance() {
        return attendanceRepository.findAll().stream()
                .map(a -> modelMapper.map(a, AttendanceResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceResponseDTO getAttendanceById(Long id) {
        Attendance a = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
        return modelMapper.map(a, AttendanceResponseDTO.class);
    }

    @Override
    public AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO requestDTO) {
        Attendance a = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
        if (requestDTO.getStatus() != null) a.setStatus(requestDTO.getStatus());
        if (requestDTO.getDate() != null) a.setDate(requestDTO.getDate());
        return modelMapper.map(attendanceRepository.save(a), AttendanceResponseDTO.class);
    }

    @Override
    public void deleteAttendance(Long id) {
        Attendance a = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
        attendanceRepository.delete(a);
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId).stream()
                .map(a -> modelMapper.map(a, AttendanceResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Double getAttendancePercentageByStudentId(Long studentId) {
        List<Attendance> records = attendanceRepository.findByStudentId(studentId);
        if (records.isEmpty()) return 0.0;
        long presentCount = records.stream().filter(a -> a.getStatus() == AttendanceStatus.PRESENT).count();
        return (double) presentCount / records.size() * 100.0;
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByBatchId(Long batchId) {
        List<Long> studentIds = studentBatchRepository.findByBatchId(batchId).stream()
                .map(sb -> sb.getStudent().getId())
                .collect(Collectors.toList());
        return attendanceRepository.findAll().stream()
                .filter(a -> studentIds.contains(a.getStudent().getId()))
                .map(a -> modelMapper.map(a, AttendanceResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date).stream()
                .map(a -> modelMapper.map(a, AttendanceResponseDTO.class))
                .collect(Collectors.toList());
    }
}
