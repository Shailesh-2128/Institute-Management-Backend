package com.InstituteManagement.InstituteManagement.notification.DTO;

import com.InstituteManagement.InstituteManagement.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {
    private String title;
    private String message;
    private Role role;
}
