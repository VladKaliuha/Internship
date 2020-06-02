package com.nure.internship.encrypting.controller;

import com.nure.internship.encrypting.entity.EncryptedMessage;
import com.nure.internship.encrypting.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity getAllMessages() {
        List<EncryptedMessage> allMessages = messageService.getAll();
        return ResponseEntity.ok(allMessages);
    }
}
