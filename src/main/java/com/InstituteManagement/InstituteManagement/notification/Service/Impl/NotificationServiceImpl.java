package com.InstituteManagement.InstituteManagement.notification.Service.Impl;

import com.InstituteManagement.InstituteManagement.common.enums.Role;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.notification.DTO.NotificationRequestDTO;
import com.InstituteManagement.InstituteManagement.notification.DTO.NotificationResponseDTO;
import com.InstituteManagement.InstituteManagement.notification.Entity.Notification;
import com.InstituteManagement.InstituteManagement.notification.Repository.NotificationRepository;
import com.InstituteManagement.InstituteManagement.notification.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO requestDTO) {
        Notification notification = Notification.builder()
                .title(requestDTO.getTitle())
                .message(requestDTO.getMessage())
                .role(requestDTO.getRole())
                .build();
        return modelMapper.map(notificationRepository.save(notification), NotificationResponseDTO.class);
    }

    @Override
    public List<NotificationResponseDTO> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(n -> modelMapper.map(n, NotificationResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDTO getNotificationById(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        return modelMapper.map(n, NotificationResponseDTO.class);
    }

    @Override
    public void deleteNotification(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        notificationRepository.delete(n);
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByRole(Role role) {
        return notificationRepository.findByRole(role).stream()
                .map(n -> modelMapper.map(n, NotificationResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDTO> getRecentNotifications() {
        return notificationRepository.findTop10ByOrderByCreatedAtDesc().stream()
                .map(n -> modelMapper.map(n, NotificationResponseDTO.class))
                .collect(Collectors.toList());
    }
}
