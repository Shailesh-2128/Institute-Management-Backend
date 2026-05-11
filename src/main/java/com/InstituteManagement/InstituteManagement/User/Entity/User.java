package com.InstituteManagement.InstituteManagement.User.Entity;


import com.InstituteManagement.InstituteManagement.common.enums.UserRoles;
import com.InstituteManagement.InstituteManagement.common.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
