package com.abc_berkut.utils;

import com.abc_berkut.model.User;
import com.abc_berkut.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final UserRepository userRepository;

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String incoming = update.getMessage().getText().trim();

            User user = userRepository.findByTelegramToken(incoming);
            if (user != null) {
                user.setTelegramChatId(update.getMessage().getChatId());
                userRepository.save(user);

                sendMessage(update.getMessage().getChatId(), "Токен успешно привязан к вашему аккаунту!");
            } else {
                sendMessage(update.getMessage().getChatId(), "Токен не найден. Сначала сгенерируйте токен в личном кабинете.");
            }
        }
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId.toString());
        msg.setText(text);
        try {
            execute(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}