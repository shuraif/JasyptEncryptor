package com.ms.jasyptencryptor.controller;

import com.ms.jasyptencryptor.modal.request.EncryptionRequest;
import com.ms.jasyptencryptor.modal.response.EncryptionResponse;
import com.ms.jasyptencryptor.service.EncryptorService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptorController {

  Logger logger = LoggerFactory.getLogger(EncryptorController.class);

  private final Map<String, EncryptorService> encryptorServiceMap;

  @Autowired
  public EncryptorController(Map<String, EncryptorService> encryptorServiceMap) {
    this.encryptorServiceMap = encryptorServiceMap;
  }

  @GetMapping("/encryptor/encrypt")
  public EncryptionResponse encrypt(@RequestBody EncryptionRequest request) {

    EncryptorService service = encryptorServiceMap.get(request.getAlgorithm());
    if (service == null) {
      logger.info("Unknown encryption algorithm: " + request.getAlgorithm());
      logger.info("Using default SHA256AndAESEncryptorServiceImpl");
      service = encryptorServiceMap.get("SHA256AndAES");
    }
    return service.encrypt(request.getPlainTextSecret());

  }

  @GetMapping("/encryptor/decrypt")
  public EncryptionResponse decrypt(@RequestBody EncryptionRequest request) {

   EncryptorService service = encryptorServiceMap.get(request.getAlgorithm());
    if (service == null) {
      logger.info("Unknown decryption algorithm: " + request.getAlgorithm());
      logger.info("Using default SHA256AndAESEncryptorServiceImpl");
      service = encryptorServiceMap.get("SHA256AndAES");
    }
    return service.decrypt(request.getEncryptedSecret());

  }

}
