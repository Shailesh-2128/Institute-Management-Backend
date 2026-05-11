package com.InstituteManagement.InstituteManagement.attendance.Entity;

import com.InstituteManagement.InstituteManagement.common.enums.AttendanceStatus;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="attendance")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
