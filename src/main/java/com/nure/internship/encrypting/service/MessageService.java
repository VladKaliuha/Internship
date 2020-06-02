package com.nure.internship.encrypting.service;

import com.nure.internship.encrypting.entity.EncryptedMessage;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    void saveMessage(String encryptedMessage);

    Optional<EncryptedMessage> getMessageById(Integer id);


    List<EncryptedMessage> getAll();
}
