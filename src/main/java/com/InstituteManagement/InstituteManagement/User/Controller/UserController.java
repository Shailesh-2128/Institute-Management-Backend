package com.InstituteManagement.InstituteManagement.User.Controller;


import com.InstituteManagement.InstituteManagement.User.DTO.UserResponseDTO;
import com.InstituteManagement.InstituteManagement.User.Service.UserService;
import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.InstituteManagement.InstituteManagement.User.DTO.UserRequestDTO;
import com.InstituteManagement.InstituteManagement.User.DTO.ChangePasswordDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name="User Api's",description = "Mange user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ApiResponse<UserResponseDTO> getUserByID(@PathVariable Long id)
    {
        UserResponseDTO user = userService.getUserById(id);

        return ApiResponse.<UserResponseDTO>builder().success(true).data(user).message("user fetch sucessfull").build();
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public ApiResponse<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO)
    {
        UserResponseDTO user = userService.createUser(userRequestDTO);

        return ApiResponse.<UserResponseDTO>builder().success(true).data(user).message("user created successfully").build();
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ApiResponse<List<UserResponseDTO>> getAllUsers() {
        return ApiResponse.<List<UserResponseDTO>>builder().success(true).data(userService.getAllUsers()).message("Users fetched successfully").build();
    }

    @Operation(summary = "Update user by id")
    @PutMapping("/{id}")
    public ApiResponse<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.<UserResponseDTO>builder().success(true).data(userService.updateUser(id, userRequestDTO)).message("User updated successfully").build();
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.<Void>builder().success(true).message("User deleted successfully").build();
    }

    @Operation(summary = "Get current user")
    @GetMapping("/me")
    public ApiResponse<UserResponseDTO> getCurrentUser() {
        return ApiResponse.<UserResponseDTO>builder().success(true).data(userService.getCurrentUser()).message("Current user fetched").build();
    }

    @Operation(summary = "Update current user")
    @PutMapping("/me")
    public ApiResponse<UserResponseDTO> updateCurrentUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.<UserResponseDTO>builder().success(true).data(userService.updateCurrentUser(userRequestDTO)).message("Current user updated").build();
    }

    @Operation(summary = "Change password")
    @PutMapping("/change-password")
    public ApiResponse<Void> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        userService.changePassword(changePasswordDTO);
        return ApiResponse.<Void>builder().success(true).message("Password changed successfully").build();
    }

    @Operation(summary = "Update user role")
    @PatchMapping("/{id}/role")
    public ApiResponse<UserResponseDTO> updateRole(@PathVariable Long id, @RequestParam String role) {
        return ApiResponse.<UserResponseDTO>builder().success(true).data(userService.updateRole(id, role)).message("User role updated").build();
    }

    @Operation(summary = "Update user status")
    @PatchMapping("/{id}/status")
    public ApiResponse<UserResponseDTO> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ApiResponse.<UserResponseDTO>builder().success(true).data(userService.updateStatus(id, status)).message("User status updated").build();
    }

    @Operation(summary = "Get users by role")
    @GetMapping("/role/{role}")
    public ApiResponse<List<UserResponseDTO>> getUsersByRole(@PathVariable String role) {
        return ApiResponse.<List<UserResponseDTO>>builder().success(true).data(userService.getUsersByRole(role)).message("Users by role fetched").build();
    }

    @Operation(summary = "Search users")
    @GetMapping("/search")
    public ApiResponse<List<UserResponseDTO>> searchUsers(@RequestParam String keyword) {
        return ApiResponse.<List<UserResponseDTO>>builder().success(true).data(userService.searchUsers(keyword)).message("Users searched successfully").build();
    }

    @Operation(summary = "Get active users")
    @GetMapping("/active")
    public ApiResponse<List<UserResponseDTO>> getActiveUsers() {
        return ApiResponse.<List<UserResponseDTO>>builder().success(true).data(userService.getActiveUsers()).message("Active users fetched").build();
    }

    @Operation(summary = "Get inactive users")
    @GetMapping("/inactive")
    public ApiResponse<List<UserResponseDTO>> getInactiveUsers() {
        return ApiResponse.<List<UserResponseDTO>>builder().success(true).data(userService.getInactiveUsers()).message("Inactive users fetched").build();
    }

    @Operation(summary = "Get total user count")
    @GetMapping("/count")
    public ApiResponse<Long> getUserCount() {
        return ApiResponse.<Long>builder().success(true).data(userService.getUserCount()).message("User count fetched").build();
    }

    @Operation(summary = "Get user stats")
    @GetMapping("/stats")
    public ApiResponse<Map<String, Object>> getUserStats() {
        return ApiResponse.<Map<String, Object>>builder().success(true).data(userService.getUserStats()).message("User stats fetched").build();
    }
}
