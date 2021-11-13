package com.rroca.encryptap.controller;

import org.springframework.http.HttpStatus;
import com.rroca.encryptap.utils.ApiResponse;
import com.rroca.encryptap.utils.EncryptData;
import org.springframework.http.ResponseEntity;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import javax.crypto.Cipher;
import javax.validation.Valid;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class DefaultController {
    @PostMapping("/encrypt")
    public ResponseEntity<ApiResponse> encryptTextAction(@Valid @RequestBody EncryptData data) {
        ApiResponse response;

        response = new ApiResponse(HttpStatus.OK.value(), "Encrypted text", encryptText(data));
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<ApiResponse> decryptTextAction(@Valid @RequestBody EncryptData data) {
        ApiResponse response;

        response = new ApiResponse(HttpStatus.OK.value(), "Decrypted text", decrypt(data));
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    /**
     * @param data the data
     * @return string
     */
    public String encryptText(EncryptData data) {
        String seed =  data.getSeed();
        String texto =  data.getTexto();
        String result = "";

        try {
            byte[] key = seed.getBytes(StandardCharsets.UTF_8);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(texto.getBytes());
            byte[] tmpByte = Base64.encodeBase64(encrypted);

            result = new String(tmpByte);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = texto;
        }

        return result;
    }

    /**
     * @param data the data
     * @return string
     */
    public String decrypt(EncryptData data) {
        String seed =  data.getSeed();
        String encryptedTexto =  data.getTexto();
        String result = "";

        try {
            byte[] key = seed.getBytes(StandardCharsets.UTF_8);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] tmpBClave = Base64.decodeBase64(encryptedTexto);
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decrypted = cipher.doFinal(tmpBClave);

            result = new String(decrypted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = encryptedTexto;
        }

        return result;
    }
}