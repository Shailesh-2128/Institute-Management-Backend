package com.InstituteManagement.InstituteManagement.User.Service;

import com.InstituteManagement.InstituteManagement.User.DTO.ChangePasswordDTO;
import com.InstituteManagement.InstituteManagement.User.DTO.UserRequestDTO;
import com.InstituteManagement.InstituteManagement.User.DTO.UserResponseDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserResponseDTO getUserById(Long id);
    UserResponseDTO createUser(UserRequestDTO requestDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO);
    void deleteUser(Long id);
    UserResponseDTO getCurrentUser();
    UserResponseDTO updateCurrentUser(UserRequestDTO requestDTO);
    void changePassword(ChangePasswordDTO changePasswordDTO);
    UserResponseDTO updateRole(Long id, String role);
    UserResponseDTO updateStatus(Long id, String status);
    List<UserResponseDTO> getUsersByRole(String role);
    List<UserResponseDTO> searchUsers(String keyword);
    List<UserResponseDTO> getActiveUsers();
    List<UserResponseDTO> getInactiveUsers();
    long getUserCount();
    Map<String, Object> getUserStats();
}
