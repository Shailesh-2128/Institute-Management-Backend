package com.InstituteManagement.InstituteManagement.User.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String Name;
    private String email;
    private String role;
    private String status;
}
