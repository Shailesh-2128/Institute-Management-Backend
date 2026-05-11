package com.InstituteManagement.InstituteManagement.User.Repository;

import com.InstituteManagement.InstituteManagement.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.InstituteManagement.InstituteManagement.common.enums.UserRoles;
import com.InstituteManagement.InstituteManagement.common.enums.UserStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByRole(UserRoles role);
    List<User> findByStatus(UserStatus status);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchUsers(@Param("keyword") String keyword);

    long countByStatus(UserStatus status);
}
