package com.abc_berkut.services;

import com.abc_berkut.model.Message;
import com.abc_berkut.model.User;
import com.abc_berkut.repository.MessageRepository;
import com.abc_berkut.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final TelegramNotificationService telegramNotificationService;

    public Message sendMessage(String text, String username) {
        User user = userRepository.findByUsername(username);
        Message message = new Message();
        message.setText(text);
        message.setUser(user);
        message.setCreatedAt(LocalDateTime.now());
        messageRepository.save(message);

        if (user.getTelegramChatId() != null) {
            String msg = (user.getName() != null ? user.getName() : user.getUsername())
                    + ", я получил от тебя сообщение:\n" + text;
            telegramNotificationService.notifyUser(user.getTelegramChatId(), msg);
        }
        return message;
    }

    public List<Message> getUserMessages(String username) {
        User user = userRepository.findByUsername(username);
        return messageRepository.findAllByUser(user);
    }
}
