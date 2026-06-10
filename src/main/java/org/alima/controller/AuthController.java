package org.alima.controller;


import org.alima.model.User;
import org.alima.repository.UserRepository;
import org.alima.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService auth, UserRepository ur, PasswordEncoder pe) {
        this.authService = auth;
        this.userRepository = ur;
        this.passwordEncoder = pe;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "کاربر با موفقیت ثبت شد";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return authService.login(user.getUsername(), user.getPassword());
    }
}
