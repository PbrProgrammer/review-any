package org.alima.security;

import org.alima.dto.UserDto;
import org.alima.exception.AlreadyExistException;
import org.alima.model.User;
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

    public String register(UserDto user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new AlreadyExistException("Username is already in use", "4005");
        }
        User userEntity = new User();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());

        userRepository.save(userEntity);
        return jwtUtils.generateToken(userEntity.getUsername());
    }
}
