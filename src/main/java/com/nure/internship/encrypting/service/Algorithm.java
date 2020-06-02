package com.nure.internship.encrypting.service;

public interface Algorithm {

    String encrypt(String plaintext, String key) throws Exception;

    String decrypt(String plaintext, String key) throws Exception;
}
