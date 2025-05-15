package com.example.miniapibiblioteczne.controlers;

import com.example.miniapibiblioteczne.dto.BorrowingDto;
import com.example.miniapibiblioteczne.encje.User;
import com.example.miniapibiblioteczne.service.BorrowingService;
import com.example.miniapibiblioteczne.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;
    private final BorrowingService borrowingService;

    @Autowired
    public UserController(UserService userService, BorrowingService borrowingService) {
        this.userService = userService;
        this.borrowingService = borrowingService;
    }

    // Rejestracja użytkownika
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }


    // Pobieranie użytkownika po ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Pobieranie wszystkich użytkowników
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Aktualizacja użytkownika
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // Usuwanie użytkownika
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Historia wypożyczeń użytkownika po ID
    @GetMapping("/{username}/borrowings")
    public ResponseEntity<List<BorrowingDto>> getUserBorrowingHistory(@PathVariable String username) {
        List<BorrowingDto> history = borrowingService.getUserBorrowingHistory(username);
        return ResponseEntity.ok(history);
    }

}
