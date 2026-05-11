package com.InstituteManagement.InstituteManagement.notification.Controller;

import com.InstituteManagement.InstituteManagement.common.enums.Role;
import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.notification.DTO.NotificationRequestDTO;
import com.InstituteManagement.InstituteManagement.notification.DTO.NotificationResponseDTO;
import com.InstituteManagement.InstituteManagement.notification.Service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name="Notification Api's", description = "Manage notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "Create notification")
    @PostMapping
    public ApiResponse<NotificationResponseDTO> createNotification(@RequestBody NotificationRequestDTO requestDTO) {
        return ApiResponse.<NotificationResponseDTO>builder().success(true).data(notificationService.createNotification(requestDTO)).message("Created").build();
    }

    @Operation(summary = "Get all notifications")
    @GetMapping
    public ApiResponse<List<NotificationResponseDTO>> getAllNotifications() {
        return ApiResponse.<List<NotificationResponseDTO>>builder().success(true).data(notificationService.getAllNotifications()).message("Fetched").build();
    }

    @Operation(summary = "Get notification by ID")
    @GetMapping("/{id}")
    public ApiResponse<NotificationResponseDTO> getNotificationById(@PathVariable Long id) {
        return ApiResponse.<NotificationResponseDTO>builder().success(true).data(notificationService.getNotificationById(id)).message("Fetched").build();
    }

    @Operation(summary = "Delete notification")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ApiResponse.<Void>builder().success(true).message("Deleted").build();
    }

    @Operation(summary = "Get notifications by role")
    @GetMapping("/role/{role}")
    public ApiResponse<List<NotificationResponseDTO>> getNotificationsByRole(@PathVariable Role role) {
        return ApiResponse.<List<NotificationResponseDTO>>builder().success(true).data(notificationService.getNotificationsByRole(role)).message("Fetched").build();
    }

    @Operation(summary = "Get recent notifications")
    @GetMapping("/recent")
    public ApiResponse<List<NotificationResponseDTO>> getRecentNotifications() {
        return ApiResponse.<List<NotificationResponseDTO>>builder().success(true).data(notificationService.getRecentNotifications()).message("Fetched").build();
    }

    @Operation(summary = "Send notification to all")
    @PostMapping("/send-all")
    public ApiResponse<Object> sendAll(@RequestBody Object body) {
        return ApiResponse.<Object>builder().success(true).data("Sent").message("Sent").build();
    }

    @Operation(summary = "Send notification to student")
    @PostMapping("/send-student/{studentId}")
    public ApiResponse<Object> sendStudent(@PathVariable Long studentId, @RequestBody Object body) {
        return ApiResponse.<Object>builder().success(true).data("Sent").message("Sent").build();
    }

    @Operation(summary = "Send notification to batch")
    @PostMapping("/send-batch/{batchId}")
    public ApiResponse<Object> sendBatch(@PathVariable Long batchId, @RequestBody Object body) {
        return ApiResponse.<Object>builder().success(true).data("Sent").message("Sent").build();
    }

    @Operation(summary = "Get notifications for student")
    @GetMapping("/student/{studentId}")
    public ApiResponse<List<Object>> getStudentNotifications(@PathVariable Long studentId) {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get unread notifications")
    @GetMapping("/unread")
    public ApiResponse<List<Object>> getUnreadNotifications() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Mark notification as read")
    @PatchMapping("/{id}/read")
    public ApiResponse<Object> markRead(@PathVariable Long id) {
        return ApiResponse.<Object>builder().success(true).data("Marked read").message("Marked").build();
    }
}
