package com.InstituteManagement.InstituteManagement.User.Service.Impl;

import com.InstituteManagement.InstituteManagement.Config.ModelMapperConfig;
import com.InstituteManagement.InstituteManagement.User.DTO.UserRequestDTO;
import com.InstituteManagement.InstituteManagement.User.DTO.UserResponseDTO;
import com.InstituteManagement.InstituteManagement.User.Entity.User;
import com.InstituteManagement.InstituteManagement.User.Repository.UserRepository;
import com.InstituteManagement.InstituteManagement.User.Service.UserService;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.User.DTO.ChangePasswordDTO;
import com.InstituteManagement.InstituteManagement.common.enums.UserRoles;
import com.InstituteManagement.InstituteManagement.common.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found"));

        return modelMapper.map(user,UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        User user = User.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .role(UserRoles.valueOf(requestDTO.getRole()))
                .password(requestDTO.getPassword())
                .status(UserStatus.ACTIVE) // Default status
                .createdAt(java.time.LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        if(requestDTO.getRole() != null) user.setRole(UserRoles.valueOf(requestDTO.getRole()));
        if(requestDTO.getPassword() != null) user.setPassword(requestDTO.getPassword());
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO getCurrentUser() {
        // Mock current user for now
        return getUserById(1L);
    }

    @Override
    public UserResponseDTO updateCurrentUser(UserRequestDTO requestDTO) {
        // Mock current user for now
        return updateUser(1L, requestDTO);
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        // Mock current user for now
        User user = userRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setPassword(changePasswordDTO.getNewPassword());
        userRepository.save(user);
    }

    @Override
    public UserResponseDTO updateRole(Long id, String role) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setRole(UserRoles.valueOf(role));
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO updateStatus(Long id, String status) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setStatus(UserStatus.valueOf(status));
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getUsersByRole(String role) {
        return userRepository.findByRole(UserRoles.valueOf(role)).stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword).stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getActiveUsers() {
        return userRepository.findByStatus(UserStatus.ACTIVE).stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getInactiveUsers() {
        return userRepository.findByStatus(UserStatus.INACTIVE).stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }

    @Override
    public Map<String, Object> getUserStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", userRepository.count());
        stats.put("active", userRepository.countByStatus(UserStatus.ACTIVE));
        stats.put("inactive", userRepository.countByStatus(UserStatus.INACTIVE));
        return stats;
    }
}
