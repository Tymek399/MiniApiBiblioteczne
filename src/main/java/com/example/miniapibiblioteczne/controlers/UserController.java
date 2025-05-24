package com.example.miniapibiblioteczne.controlers;

import com.example.miniapibiblioteczne.dto.UserDto;
import com.example.miniapibiblioteczne.encje.User;
import com.example.miniapibiblioteczne.service.BorrowingService;
import com.example.miniapibiblioteczne.service.UserService;
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

    public UserController(UserService userService, BorrowingService borrowingService) {
        this.userService = userService;
        this.borrowingService = borrowingService;
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/users/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userService.getUserByUserName(username);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(username, userDto);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}
