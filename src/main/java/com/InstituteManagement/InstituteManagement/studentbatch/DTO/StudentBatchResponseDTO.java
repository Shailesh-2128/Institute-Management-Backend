package com.InstituteManagement.InstituteManagement.studentbatch.DTO;

import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentBatchResponseDTO {
    private Long id;
    private StudentResponseDTO student;
    private BatchResponseDTO batch;
    private LocalDateTime createdAt;
}
