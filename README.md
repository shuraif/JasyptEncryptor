# Jasypt Encryption REST API

A Spring Boot REST API demonstrating text encryption and decryption using Jasypt (Java Simplified Encryption) library with multiple algorithm support.

## Overview

This API provides endpoints for encrypting and decrypting strings using different encryption algorithms. It supports multiple encryption strategies through a service map pattern, with automatic fallback to a default algorithm.

## Features

- **Multiple Encryption Algorithms**: Supports MD5AndDES and SHA256AndAES algorithms
- **Automatic Fallback**: Defaults to SHA256AndAES if an unknown algorithm is specified
- **Flexible Service Architecture**: Uses Spring's dependency injection with service mapping
- **Error Handling**: Includes proper exception handling and logging

## Supported Algorithms

1. **MD5AndDES**: Uses Jasypt's autowired StringEncryptor
2. **SHA256AndAES**: Uses StandardPBEStringEncryptor with PBEWithMD5AndDES algorithm

## Configuration

Add the following property to your `application.properties` or `application.yml`:

```properties
jasypt.encryptor.password=your-secret-key-here
```

## API Endpoints

### Encrypt Text

**Endpoint**: `GET /encryptor/encrypt`

**Request Body**:
```json
{
  "plainTextSecret": "your-plain-text-here",
  "algorithm": "MD5AndDES"
}
```

**Response**:
```json
{
  "encryptedSecret": "encrypted-string-here",
  "plainTextSecret": null,
  "algorithm": "MD5AndDES"
}
```

### Decrypt Text

**Endpoint**: `GET /encryptor/decrypt`

**Request Body**:
```json
{
  "encryptedSecret": "your-encrypted-text-here",
  "algorithm": "MD5AndDES"
}
```

**Response**:
```json
{
  "encryptedSecret": null,
  "plainTextSecret": "decrypted-plain-text-here",
  "algorithm": "MD5AndDES"
}
```

## Example Usage

### Encrypting a Password

```bash
curl -X GET http://localhost:8080/encryptor/encrypt \
  -H "Content-Type: application/json" \
  -d '{
    "plainTextSecret": "mySecretPassword",
    "algorithm": "SHA256AndAES"
  }'
```

### Decrypting a Password

```bash
curl -X GET http://localhost:8080/encryptor/decrypt \
  -H "Content-Type: application/json" \
  -d '{
    "encryptedSecret": "xMtIq8Z7/ABC123...",
    "algorithm": "SHA256AndAES"
  }'
```

## Data Models

### EncryptionRequest
- `plainTextSecret`: String to be encrypted (for encryption requests)
- `encryptedSecret`: Encrypted string to be decrypted (for decryption requests)
- `algorithm`: Encryption algorithm to use ("MD5AndDES" or "SHA256AndAES")

### EncryptionResponse
- `encryptedSecret`: The encrypted result (for encryption operations)
- `plainTextSecret`: The decrypted result (for decryption operations)
- `algorithm`: The algorithm used for the operation

## Dependencies

Make sure to include the following dependencies in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>3.0.5</version>
</dependency>
```

## Algorithm Details

- **MD5AndDES**: Uses Jasypt's default configuration with autowired StringEncryptor
- **SHA256AndAES**: Despite the service name, actually uses PBEWithMD5AndDES algorithm internally with StandardPBEStringEncryptor

## Error Handling

The API includes comprehensive error handling:
- Unknown algorithms automatically fall back to SHA256AndAES
- All exceptions are caught and logged
- Console output shows original and encrypted/decrypted values for debugging

## Security Notes

- Store the encryption password securely using environment variables or encrypted property files
- Consider using stronger algorithms for production environments
- The current implementation logs sensitive information to console - remove this in production
- Both endpoints use GET methods with request bodies, which is not RESTful best practice - consider using POST for better security

## Running the Application

1. Clone the repository
2. Set the `jasypt.encryptor.password` property
3. Run `mvn spring-boot:run`
4. The API will be available at `http://localhost:8080`
