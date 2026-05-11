package com.InstituteManagement.InstituteManagement.batch.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchRequestDTO {
    private Long courseId;
    private Long staffId;
    private String timing;
}
