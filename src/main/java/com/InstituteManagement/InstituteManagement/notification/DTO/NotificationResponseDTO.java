package com.InstituteManagement.InstituteManagement.notification.DTO;

import com.InstituteManagement.InstituteManagement.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDTO {
    private Long id;
    private String title;
    private String message;
    private Role role;
    private LocalDateTime createdAt;
}
