package com.nure.internship.encrypting.service.impl;

import com.nure.internship.encrypting.entity.EncryptedMessage;
import com.nure.internship.encrypting.repository.MessageRepository;
import com.nure.internship.encrypting.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public void saveMessage(String encryptedMessage) {
        messageRepository.save(new EncryptedMessage(encryptedMessage));
    }

    @Override
    public Optional<EncryptedMessage> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<EncryptedMessage> getAll(){
        return messageRepository.findAll();
    }
}
