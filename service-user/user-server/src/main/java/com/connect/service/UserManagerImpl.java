package com.connect.service;


import Requests.CreateUserRequest;
import Requests.UpdateUserRequest;
import Responses.UserResponse;
import com.connect.data.UserEntity;
import com.connect.data.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManagerImpl implements UserManager {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        // Map request to entity
        UserEntity entity = new UserEntity();
        entity.setName(request.getName());
        entity.setAge(request.getAge());
        entity.setSex(request.getSex());
        entity.setEmail(request.getEmail());
        entity.setPhoneNo(request.getPhoneNo());
        entity.setPasswordHash(request.getPassword()); // hash later

        entity = userRepository.save(entity);

        return mapToResponse(entity);
    }

    @Override
    public UserResponse getUserById(UUID id) {
        return userRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(UUID id, UpdateUserRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setSex(request.getSex());
        user.setPhoneNo(request.getPhoneNo());
        user.setEmail(request.getEmail());

        user = userRepository.save(user);
        return mapToResponse(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    private UserResponse mapToResponse(UserEntity entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .sex(entity.getSex())
                .phoneNo(entity.getPhoneNo())
                .email(entity.getEmail())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
