package com.InstituteManagement.InstituteManagement.batch.Service.Impl;

import com.InstituteManagement.InstituteManagement.batch.DTO.BatchRequestDTO;
import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.batch.Entity.Batch;
import com.InstituteManagement.InstituteManagement.batch.Repository.BatchRepository;
import com.InstituteManagement.InstituteManagement.batch.Service.BatchService;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.course.Entity.Course;
import com.InstituteManagement.InstituteManagement.course.Repository.CourseRepository;
import com.InstituteManagement.InstituteManagement.staff.Entity.Staff;
import com.InstituteManagement.InstituteManagement.staff.Repository.StaffRepository;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import com.InstituteManagement.InstituteManagement.student.Repository.StudentRepository;
import com.InstituteManagement.InstituteManagement.studentbatch.Repository.StudentBatchRepository;
import com.InstituteManagement.InstituteManagement.studentbatch.Entity.StudentBatch;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final CourseRepository courseRepository;
    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;
    private final StudentBatchRepository studentBatchRepository;
    private final ModelMapper modelMapper;

    @Override
    public BatchResponseDTO createBatch(BatchRequestDTO requestDTO) {
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        Staff staff = staffRepository.findById(requestDTO.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));

        Batch batch = Batch.builder()
                .course(course)
                .staff(staff)
                .timing(requestDTO.getTiming())
                .build();
        return modelMapper.map(batchRepository.save(batch), BatchResponseDTO.class);
    }

    @Override
    public List<BatchResponseDTO> getAllBatches() {
        return batchRepository.findAll().stream()
                .map(batch -> modelMapper.map(batch, BatchResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BatchResponseDTO getBatchById(Long id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
        return modelMapper.map(batch, BatchResponseDTO.class);
    }

    @Override
    public BatchResponseDTO updateBatch(Long id, BatchRequestDTO requestDTO) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
        
        if (requestDTO.getCourseId() != null) {
            Course course = courseRepository.findById(requestDTO.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
            batch.setCourse(course);
        }
        if (requestDTO.getStaffId() != null) {
            Staff staff = staffRepository.findById(requestDTO.getStaffId())
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));
            batch.setStaff(staff);
        }
        if (requestDTO.getTiming() != null) {
            batch.setTiming(requestDTO.getTiming());
        }

        return modelMapper.map(batchRepository.save(batch), BatchResponseDTO.class);
    }

    @Override
    public void deleteBatch(Long id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
        batchRepository.delete(batch);
    }

    @Override
    public List<BatchResponseDTO> searchBatches(String keyword) {
        return batchRepository.findAll().stream()
                .filter(b -> b.getTiming().toLowerCase().contains(keyword.toLowerCase()))
                .map(batch -> modelMapper.map(batch, BatchResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BatchResponseDTO> getBatchesByCourse(Long courseId) {
        return batchRepository.findAll().stream()
                .filter(b -> b.getCourse().getId().equals(courseId))
                .map(batch -> modelMapper.map(batch, BatchResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BatchResponseDTO> getBatchesByStaff(Long staffId) {
        return batchRepository.findAll().stream()
                .filter(b -> b.getStaff().getId().equals(staffId))
                .map(batch -> modelMapper.map(batch, BatchResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> getStudentsInBatch(Long id) {
        return studentBatchRepository.findByBatchId(id).stream()
                .map(sb -> modelMapper.map(sb.getStudent(), StudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addStudentToBatch(Long batchId, Long studentId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        
        StudentBatch sb = StudentBatch.builder()
                .student(student)
                .batch(batch)
                .build();
        studentBatchRepository.save(sb);
    }

    @Override
    public void removeStudentFromBatch(Long batchId, Long studentId) {
        studentBatchRepository.findByBatchId(batchId).stream()
            .filter(sb -> sb.getStudent().getId().equals(studentId))
            .findFirst()
            .ifPresent(studentBatchRepository::delete);
    }

    @Override
    public long getBatchCount() {
        return batchRepository.count();
    }

    @Override
    public Map<String, Object> getBatchStats(Long id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalStudents", studentBatchRepository.findByBatchId(id).size());
        stats.put("courseName", batch.getCourse().getName());
        return stats;
    }
}
