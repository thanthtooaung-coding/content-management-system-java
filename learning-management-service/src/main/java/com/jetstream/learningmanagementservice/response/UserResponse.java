package com.jetstream.learningmanagementservice.response;

import com.jetstream.learningmanagementservice.model.User;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String role;

    public static UserResponse fromEntity(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        if (user.getRole() != null)
            dto.setRole(user.getRole().getName());
        return dto;
    }
}