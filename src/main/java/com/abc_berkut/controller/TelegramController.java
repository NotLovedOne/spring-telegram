package com.abc_berkut.controller;

import com.abc_berkut.dto.TelegramTokenResponse;
import com.abc_berkut.services.TelegramTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
public class TelegramController {

    private final TelegramTokenService telegramTokenService;

    @PostMapping("/token")
    public TelegramTokenResponse generateTelegramToken(Principal principal) {
        String token = telegramTokenService.generateToken(principal.getName());
        return new TelegramTokenResponse(token);
    }
}
