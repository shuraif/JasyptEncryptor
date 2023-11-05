package com.ms.enc;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;


public class JasyptEncryptor {

	private static String JASYPT_MASTER_PASSWORD = "MasterPassword";
	private static String JASYPT_ENCRYPTION_ALGORITHM = "PBEWithMD5AndDES";

	public static void main(String[] args) throws Exception {

		
		String currentPassword = "value to be encrypted";

		try {
			
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(JASYPT_MASTER_PASSWORD);
			encryptor.setAlgorithm(JASYPT_ENCRYPTION_ALGORITHM);
			
			String encryptedPassword = "ENC(" + encryptor.encrypt(currentPassword) + ")";	
			System.out.println("Original Password:" + currentPassword + " | " + "Encrypted Password:"
					+ encryptedPassword + "\n");
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


}
