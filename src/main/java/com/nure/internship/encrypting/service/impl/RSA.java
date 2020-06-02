package com.nure.internship.encrypting.service.impl;

import com.nure.internship.encrypting.service.Algorithm;
import com.nure.internship.encrypting.util.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
@Service
public class RSA implements Algorithm {

    public String encrypt(String text, String pubKey) throws Exception {
        byte[] pubKeyByte = RSAUtils.stringToBytes(pubKey);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = null;
        try {
            publicKey = kf.generatePublic(new X509EncodedKeySpec(pubKeyByte));
        } catch (InvalidKeySpecException e) {
            log.warn(e.getMessage());
        }

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes());

        return RSAUtils.bytesToString(encryptedBytes);
    }

    public String decrypt(String encryptedText, String privKey) throws Exception {
        byte[] privKeyByte = RSAUtils.stringToBytes(privKey);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = null;
        try {
            privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privKeyByte));
        } catch (InvalidKeySpecException e) {
            log.warn(e.getMessage());
        }

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(RSAUtils.stringToBytes(encryptedText));
        return new String(decryptedBytes);
    }

}
