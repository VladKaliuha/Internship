package com.nure.internship.encrypting.util;

import lombok.Data;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

public class RSAUtils {

    private RSAUtils() {
        throw new UnsupportedOperationException();
    }

    @Data
    public static class KeyPairStrings {
        private String pubKey;
        private String privKey;
    }

    public static KeyPairStrings getKeyPair() throws NoSuchAlgorithmException {
        KeyPairStrings keyPairStrings = new KeyPairStrings();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.genKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();

        keyPairStrings.pubKey = RSAUtils.bytesToString(publicKeyBytes);
        keyPairStrings.privKey = RSAUtils.bytesToString(privateKeyBytes);

        return keyPairStrings;
    }

    public static String bytesToString(byte[] b) {
        byte[] b2 = new byte[b.length + 1];
        b2[0] = 1;
        System.arraycopy(b, 0, b2, 1, b.length);
        return new BigInteger(b2).toString(36);
    }

    public static byte[] stringToBytes(String s) {
        byte[] b2 = new BigInteger(s, 36).toByteArray();
        return Arrays.copyOfRange(b2, 1, b2.length);
    }
}
