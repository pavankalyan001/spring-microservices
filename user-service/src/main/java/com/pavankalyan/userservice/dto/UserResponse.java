package com.pavankalyan.userservice.dto;

import com.pavankalyan.userservice.model.User;
import java.time.LocalDateTime;

public record UserResponse(
    Long id,
    String name,
    String email,
    LocalDateTime createdAt
) {
    public static UserResponse fromEntity(User user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getCreatedAt()
        );
    }
}
