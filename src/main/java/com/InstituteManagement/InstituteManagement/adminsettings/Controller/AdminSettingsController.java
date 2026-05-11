package com.InstituteManagement.InstituteManagement.adminsettings.Controller;

import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name="Admin Settings Api's", description = "System settings, roles, permissions, audit logs")
public class AdminSettingsController {

    // Audit Logs
    @Operation(summary = "Get all audit logs")
    @GetMapping("/audit-logs")
    public ApiResponse<List<Object>> getAuditLogs() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get audit logs by user")
    @GetMapping("/audit-logs/user/{userId}")
    public ApiResponse<List<Object>> getAuditLogsByUser(@PathVariable Long userId) {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    // Roles
    @Operation(summary = "Get all roles")
    @GetMapping("/roles")
    public ApiResponse<List<Object>> getRoles() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Create role")
    @PostMapping("/roles")
    public ApiResponse<Object> createRole(@RequestBody Object role) {
        return ApiResponse.<Object>builder().success(true).data(role).message("Created").build();
    }

    @Operation(summary = "Update role")
    @PutMapping("/roles/{id}")
    public ApiResponse<Object> updateRole(@PathVariable Long id, @RequestBody Object role) {
        return ApiResponse.<Object>builder().success(true).data(role).message("Updated").build();
    }

    // Permissions
    @Operation(summary = "Get all permissions")
    @GetMapping("/permissions")
    public ApiResponse<List<Object>> getPermissions() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Create permission")
    @PostMapping("/permissions")
    public ApiResponse<Object> createPermission(@RequestBody Object permission) {
        return ApiResponse.<Object>builder().success(true).data(permission).message("Created").build();
    }

    // Settings
    @Operation(summary = "Get all settings")
    @GetMapping("/settings")
    public ApiResponse<Object> getSettings() {
        return ApiResponse.<Object>builder().success(true).data("Settings").message("Fetched").build();
    }

    @Operation(summary = "Update settings")
    @PutMapping("/settings")
    public ApiResponse<Object> updateSettings(@RequestBody Object settings) {
        return ApiResponse.<Object>builder().success(true).data(settings).message("Updated").build();
    }
}
