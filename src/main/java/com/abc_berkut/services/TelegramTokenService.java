package com.abc_berkut.services;

import com.abc_berkut.model.User;
import com.abc_berkut.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TelegramTokenService {

    private final UserRepository userRepository;

    public String generateToken(String username) {
        User user = userRepository.findByUsername(username);
        String token = UUID.randomUUID().toString();
        user.setTelegramToken(token);
        userRepository.save(user);
        return token;
    }
}
