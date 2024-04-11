package com.blog.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public User register(@RequestParam String username, @RequestParam String password) {
        return userService.register(username, password);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String username) {
        userService.logout(username);
    }
}
