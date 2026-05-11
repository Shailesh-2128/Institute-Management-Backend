package com.InstituteManagement.InstituteManagement.student.Service.Impl;

import com.InstituteManagement.InstituteManagement.User.Entity.User;
import com.InstituteManagement.InstituteManagement.User.Repository.UserRepository;
import com.InstituteManagement.InstituteManagement.common.enums.Status;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.batch.Entity.Batch;
import com.InstituteManagement.InstituteManagement.batch.Repository.BatchRepository;
import com.InstituteManagement.InstituteManagement.course.DTO.CourseResponseDTO;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentRequestDTO;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import com.InstituteManagement.InstituteManagement.student.Repository.StudentRepository;
import com.InstituteManagement.InstituteManagement.student.Service.StudentService;
import com.InstituteManagement.InstituteManagement.studentbatch.Repository.StudentBatchRepository;
import com.InstituteManagement.InstituteManagement.studentbatch.Entity.StudentBatch;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentBatchRepository studentBatchRepository;
    private final BatchRepository batchRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDTO.getUserId()));

        Student student = Student.builder()
                .user(user)
                .dob(requestDTO.getDob())
                .address(requestDTO.getAddress())
                .status(Status.ACTIVE)
                .build();

        return modelMapper.map(studentRepository.save(student), StudentResponseDTO.class);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        if (requestDTO.getUserId() != null) {
            User user = userRepository.findById(requestDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDTO.getUserId()));
            student.setUser(user);
        }
        
        if (requestDTO.getDob() != null) {
            student.setDob(requestDTO.getDob());
        }
        if (requestDTO.getAddress() != null) {
            student.setAddress(requestDTO.getAddress());
        }

        return modelMapper.map(studentRepository.save(student), StudentResponseDTO.class);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    @Override
    public StudentResponseDTO getCurrentStudent() {
        return getStudentById(1L); // Mock ID
    }

    @Override
    public StudentResponseDTO updateCurrentStudent(StudentRequestDTO requestDTO) {
        return updateStudent(1L, requestDTO); // Mock ID
    }

    @Override
    public List<StudentResponseDTO> searchStudents(String keyword) {
        return studentRepository.searchStudents(keyword).stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> getActiveStudents() {
        return studentRepository.findByStatus(Status.ACTIVE).stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> getInactiveStudents() {
        return studentRepository.findByStatus(Status.INACTIVE).stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BatchResponseDTO> getBatchesForStudent(Long id) {
        return studentBatchRepository.findByStudentId(id).stream()
                .map(sb -> modelMapper.map(sb.getBatch(), BatchResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getCoursesForStudent(Long id) {
        return studentBatchRepository.findByStudentId(id).stream()
                .map(sb -> sb.getBatch().getCourse())
                .distinct()
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO assignBatchToStudent(Long studentId, Long batchId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        com.InstituteManagement.InstituteManagement.batch.Entity.Batch batch = 
            batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
        
        StudentBatch sb = StudentBatch.builder()
                .student(student)
                .batch(batch)
                .build();
        studentBatchRepository.save(sb);
        return getStudentById(studentId);
    }

    @Override
    public StudentResponseDTO removeBatchFromStudent(Long studentId, Long batchId) {
        studentBatchRepository.findByStudentId(studentId).stream()
            .filter(sb -> sb.getBatch().getId().equals(batchId))
            .findFirst()
            .ifPresent(studentBatchRepository::delete);
        return getStudentById(studentId);
    }
}
