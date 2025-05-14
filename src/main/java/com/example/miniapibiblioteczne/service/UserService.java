package com.example.miniapibiblioteczne.service;

import com.example.miniapibiblioteczne.model.User;
import com.example.miniapibiblioteczne.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Rejestracja użytkownika
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Autoryzacja użytkownika
    public String authenticateUser(String userName, String password) {
        // W tej metodzie można dodać logikę autoryzacji, np. porównanie hasła z zapisanym hasłem
        Optional<User> userOpt = userRepository.findByUserName(userName);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            // Możesz dodać generowanie tokenu JWT tutaj
            return "Token"; // Placeholder token
        }
        return "Invalid credentials";
    }

    // Pobieranie wszystkich użytkowników
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Pobieranie użytkownika po ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Aktualizacja użytkownika
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    // Usuwanie użytkownika
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}