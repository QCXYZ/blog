package com.blog.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return generateToken(user);
            }
        }
        throw new IllegalArgumentException("用户名或密码无效");
    }

    public User register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        return userRepository.save(newUser);
    }

    public void logout(String username) {
        // 通常由前端实现
    }

    private String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .sign(Algorithm.HMAC256("your_secret_key"));
    }
}
