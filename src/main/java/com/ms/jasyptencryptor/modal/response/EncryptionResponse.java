package com.ms.jasyptencryptor.modal.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EncryptionResponse {

  private String encryptedSecret;
  private String plainTextSecret;
  private String algorithm;

}
