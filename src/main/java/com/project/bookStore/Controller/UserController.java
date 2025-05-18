package com.project.bookStore.Controller;

import com.project.bookStore.Entity.User;
import com.project.bookStore.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService svc) {
        this.userService = svc;
    }

    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        return userService.register(username, password);
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        Optional<User> user = userService.login(username, password);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

