package com.ms.jasyptencryptor.service;

import com.ms.jasyptencryptor.modal.response.EncryptionResponse;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MD5AndDES")
public class SHA256AndAESEncryptorServiceImpl implements EncryptorService {

  @Autowired
  private StringEncryptor jasyptStringEncryptor;

  @Override
  public EncryptionResponse encrypt(String plainTextSecret) {
    String encryptedPassword = "";
    EncryptionResponse response = new EncryptionResponse();

    try {

      encryptedPassword = jasyptStringEncryptor.encrypt(plainTextSecret);
      System.out.println("Original Password: " + plainTextSecret + " | " +
          "Encrypted Password: " + encryptedPassword + "\n");

      response.setEncryptedSecret(encryptedPassword);
      response.setAlgorithm("MD5AndDES");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return response;
  }

  @Override
  public EncryptionResponse decrypt(String encryptedSecret) {

    EncryptionResponse response = new EncryptionResponse();
    String decryptedPassword = "";

    try {

      decryptedPassword = jasyptStringEncryptor.decrypt(encryptedSecret);
      System.out.println("Original Password:" + encryptedSecret + " | " + "Encrypted Password:"
          + decryptedPassword + "\n");

      response.setPlainTextSecret(decryptedPassword);
      response.setAlgorithm("MD5AndDES");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return response;

  }

}

