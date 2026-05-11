package com.InstituteManagement.InstituteManagement.payment.Entity;

import com.InstituteManagement.InstituteManagement.common.enums.PaymentMode;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="payments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMode mode;

    private LocalDateTime date;

    @PrePersist
    public void prePersist() {
        this.date = LocalDateTime.now();
    }
}
