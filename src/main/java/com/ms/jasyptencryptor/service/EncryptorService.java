package com.ms.jasyptencryptor.service;

import com.ms.jasyptencryptor.modal.response.EncryptionResponse;

public interface EncryptorService {

  public EncryptionResponse encrypt(String plainTextSecret);
  public EncryptionResponse decrypt(String encryptedSecret);

}