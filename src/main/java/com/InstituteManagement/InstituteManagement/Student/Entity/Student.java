package com.InstituteManagement.InstituteManagement.student.Entity;

import com.InstituteManagement.InstituteManagement.User.Entity.User;
import com.InstituteManagement.InstituteManagement.common.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="students")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.admissionDate == null) {
            this.admissionDate = LocalDate.now();
        }

        if (this.status == null) {
            this.status = Status.ACTIVE;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
