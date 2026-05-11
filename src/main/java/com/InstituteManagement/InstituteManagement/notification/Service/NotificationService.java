package com.InstituteManagement.InstituteManagement.notification.Service;

import com.InstituteManagement.InstituteManagement.common.enums.Role;
import com.InstituteManagement.InstituteManagement.notification.DTO.NotificationRequestDTO;
import com.InstituteManagement.InstituteManagement.notification.DTO.NotificationResponseDTO;
import java.util.List;

public interface NotificationService {
    NotificationResponseDTO createNotification(NotificationRequestDTO requestDTO);
    List<NotificationResponseDTO> getAllNotifications();
    NotificationResponseDTO getNotificationById(Long id);
    void deleteNotification(Long id);
    List<NotificationResponseDTO> getNotificationsByRole(Role role);
    List<NotificationResponseDTO> getRecentNotifications();
}
