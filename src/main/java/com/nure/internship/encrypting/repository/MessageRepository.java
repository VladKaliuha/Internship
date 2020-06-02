package com.nure.internship.encrypting.repository;

import com.nure.internship.encrypting.entity.EncryptedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<EncryptedMessage, Integer> {
}
