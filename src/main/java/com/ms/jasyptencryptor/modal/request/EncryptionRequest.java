package com.ms.jasyptencryptor.modal.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EncryptionRequest {

  private String plainTextSecret;
  private String encryptedSecret;
  private String algorithm;

}
