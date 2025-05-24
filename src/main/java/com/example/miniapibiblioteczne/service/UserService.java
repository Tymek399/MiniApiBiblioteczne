package com.example.miniapibiblioteczne.service;

import com.example.miniapibiblioteczne.dto.UserDto;
import com.example.miniapibiblioteczne.encje.User;
import com.example.miniapibiblioteczne.enums.Role;
import com.example.miniapibiblioteczne.exceptions.ResourceNotFoundException;
import com.example.miniapibiblioteczne.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(UserDto userDto) {
        if (usernameExists(userDto.getUserName())) {
            throw new IllegalArgumentException("username already exists");
        }
        if (emailExists(userDto.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encodePassword(userDto.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUserName(username);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }


    public UserDto getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserDto.fromEntity(user);
    }

    public User updateUser(String username, UserDto userDto) {
        User existingUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!existingUser.getUserName().equals(userDto.getUserName())) {
            if (userRepository.existsByUserName(userDto.getUserName())) {
                throw new IllegalArgumentException("Username is already taken");
            }
            existingUser.setUserName(userDto.getUserName());
        }

        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            existingUser.setPassword(encodePassword(userDto.getPassword()));
        }

        existingUser.setEmail(userDto.getEmail());
        existingUser.setRole(Role.valueOf(userDto.getRole()));

        return userRepository.save(existingUser);
    }

    public void deleteUser(String username) {
        userRepository.deleteUserByUserName(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}