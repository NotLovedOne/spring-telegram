package com.abc_berkut.services;

public interface TelegramNotificationService {
    void notifyUser(Long chatId, String text);
}
