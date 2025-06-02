package com.abc_berkut.controller;

import com.abc_berkut.dto.SendMessageRequest;
import com.abc_berkut.model.Message;
import com.abc_berkut.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestBody SendMessageRequest request, Principal principal) {
        return messageService.sendMessage(request.getText(), principal.getName());
    }

    @GetMapping
    public List<Message> getMessages(Principal principal) {
        return messageService.getUserMessages(principal.getName());
    }
}
