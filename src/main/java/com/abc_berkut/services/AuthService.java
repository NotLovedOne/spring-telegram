package com.abc_berkut.services;

import com.abc_berkut.model.User;
import com.abc_berkut.repository.UserRepository;
import com.abc_berkut.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(String username, String password, String name) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Пользователь уже существует");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        userRepository.save(user);
        return "Успешно зарегистрирован";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Неверный логин или пароль");
        }
        return jwtUtil.generateToken(user.getUsername());
    }
}
