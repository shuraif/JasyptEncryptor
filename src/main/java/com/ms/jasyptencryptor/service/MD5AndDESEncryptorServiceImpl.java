package com.ms.jasyptencryptor.service;

import com.ms.jasyptencryptor.modal.response.EncryptionResponse;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("MD5AndDES")
public class MD5AndDESEncryptorServiceImpl implements EncryptorService {


  private static String JASYPT_ENCRYPTION_ALGORITHM = "PBEWithMD5AndDES";

  @Value("${jasypt.encryptor.password}")
  private String encryptorPassword;


  @Override
  public EncryptionResponse encrypt(String planTextsecret) {

    EncryptionResponse response = new EncryptionResponse();
    try {

      StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
      encryptor.setPassword(encryptorPassword);
      encryptor.setAlgorithm(JASYPT_ENCRYPTION_ALGORITHM);

      String encryptedPassword = encryptor.encrypt(planTextsecret);
      System.out.println("Original Password:" + planTextsecret + " | " + "Encrypted Password:"
          + encryptedPassword + "\n");

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
    try {

      StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
      encryptor.setPassword(encryptorPassword);
      encryptor.setAlgorithm(JASYPT_ENCRYPTION_ALGORITHM);

      String decryptedPassword = encryptor.decrypt(encryptedSecret);
      System.out.println("Original Password:" + encryptedSecret + " | " + "decrypted Password:"
          + decryptedPassword + "\n");

      response.setPlainTextSecret(decryptedPassword);
      response.setAlgorithm("MD5AndDES");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return response;
  }
}
