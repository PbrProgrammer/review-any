package org.alima.security;


import lombok.RequiredArgsConstructor;
import org.alima.dto.UserDto;
import org.alima.exception.AlreadyExistException;
import org.alima.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto user) {
        return authService.login(user.getUsername(), user.getPassword());
    }
}
