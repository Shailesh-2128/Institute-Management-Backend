package com.InstituteManagement.InstituteManagement.studentbatch.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentBatchRequestDTO {
    private Long studentId;
    private Long batchId;
}
