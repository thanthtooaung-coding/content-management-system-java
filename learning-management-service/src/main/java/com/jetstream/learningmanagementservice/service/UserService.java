package com.jetstream.learningmanagementservice.service;

import com.jetstream.learningmanagementservice.exception.ResourceNotFoundException;
import com.jetstream.learningmanagementservice.model.Role;
import com.jetstream.learningmanagementservice.model.User;
import com.jetstream.learningmanagementservice.repository.RoleRepository;
import com.jetstream.learningmanagementservice.repository.UserRepository;
import com.jetstream.learningmanagementservice.request.UserCreateRequest;
import com.jetstream.learningmanagementservice.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RabbitTemplate rabbitTemplate;

    public UserResponse registerUser(UserCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRegistrationDate(LocalDateTime.now());

        Role userRole = roleRepository.findByName(request.getRoleName() != null ? request.getRoleName().toUpperCase() : "STUDENT")
            .orElseThrow(() -> new ResourceNotFoundException("Role", "name", request.getRoleName()));
        user.setRole(userRole);
        
        User savedUser = userRepository.save(user);

        return UserResponse.fromEntity(savedUser);
    }
}