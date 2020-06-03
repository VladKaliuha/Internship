package com.nure.internship.encrypting.controller;

import com.nure.internship.encrypting.dto.MessageAndKeyDTO;
import com.nure.internship.encrypting.entity.EncryptedMessage;
import com.nure.internship.encrypting.service.Algorithm;
import com.nure.internship.encrypting.service.MessageService;
import com.nure.internship.encrypting.util.RSAUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RestController
@RequestMapping("/rsa")
public class RsaAlgorithmController {

    @Autowired
    @Qualifier("RSA")
    private Algorithm rsa;
    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity decode(@RequestParam String key, @RequestParam Integer messageId) {
        Optional<EncryptedMessage> messageById = messageService.getMessageById(messageId);
        AtomicReference<ResponseEntity> response = new AtomicReference<>(ResponseEntity.badRequest().build());
        messageById.ifPresent(message ->
                {
                    try {
                        response.set(ResponseEntity.ok(rsa.decrypt(message.getMessage(), key)));
                    } catch (Exception e) {
                        log.warn(e.getMessage());
                    }
                }
        );
        return response.get();
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity encode(@RequestBody String message) {
        RSAUtils.KeyPairStrings keyPairStrings = RSAUtils.getKeyPair();
        String encryptedMessage = rsa.encrypt(message.substring(1, message.length() - 1), keyPairStrings.getPubKey());
        messageService.saveMessage(encryptedMessage);
        return ResponseEntity.ok(new MessageAndKeyDTO(keyPairStrings.getPrivKey(), encryptedMessage));
    }
}
