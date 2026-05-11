package com.InstituteManagement.InstituteManagement.fees.Entity;

import com.InstituteManagement.InstituteManagement.common.enums.FeeStatus;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="fees")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "paid_amount", nullable = false)
    private BigDecimal paidAmount;

    @Column(name = "due_amount", nullable = false)
    private BigDecimal dueAmount;

    @Enumerated(EnumType.STRING)
    private FeeStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
