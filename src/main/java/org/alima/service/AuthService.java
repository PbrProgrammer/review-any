package org.alima.service;

import org.alima.model.User;
import org.alima.repository.UserRepository;
import org.alima.util.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository ur, PasswordEncoder pe, JwtUtils ju) {
        this.userRepository = ur;
        this.passwordEncoder = pe;
        this.jwtUtils = ju;
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("کاربر پیدا نشد"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("رمز عبور اشتباه است");
        }
        return jwtUtils.generateToken(username);
    }
}
