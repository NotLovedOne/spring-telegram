package com.abc_berkut.services;

import com.abc_berkut.utils.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramNotificationServiceImpl implements TelegramNotificationService {
    private final TelegramBot telegramBot;

    @Override
    public void notifyUser(Long chatId, String text) {
        telegramBot.sendMessage(chatId, text);
    }
}
