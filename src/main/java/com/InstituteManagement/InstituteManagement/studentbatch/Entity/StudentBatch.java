package com.InstituteManagement.InstituteManagement.studentbatch.Entity;

import com.InstituteManagement.InstituteManagement.batch.Entity.Batch;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="student_batches")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
