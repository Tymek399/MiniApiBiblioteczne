package com.example.miniapibiblioteczne.controlers;

import com.example.miniapibiblioteczne.dto.UserDto;
import com.example.miniapibiblioteczne.encje.User;
import com.example.miniapibiblioteczne.mapper.UserMapper;
import com.example.miniapibiblioteczne.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserDto registerUser(@Valid @RequestBody UserDto userDto) {
        User registeredUser = userService.registerUser(userDto);
        return userMapper.toDto(registeredUser);
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
