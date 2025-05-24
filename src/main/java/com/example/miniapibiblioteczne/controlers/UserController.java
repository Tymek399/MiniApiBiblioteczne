package com.example.miniapibiblioteczne.controlers;

import com.example.miniapibiblioteczne.dto.UserDto;
import com.example.miniapibiblioteczne.encje.User;
import com.example.miniapibiblioteczne.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
