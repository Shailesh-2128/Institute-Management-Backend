package com.InstituteManagement.InstituteManagement.notification.Repository;

import com.InstituteManagement.InstituteManagement.common.enums.Role;
import com.InstituteManagement.InstituteManagement.notification.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRole(Role role);
    List<Notification> findTop10ByOrderByCreatedAtDesc();
}
