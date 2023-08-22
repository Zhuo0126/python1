package com.springboot.demo.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
@Component
public class RSA {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSA(){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024,new SecureRandom());
            KeyPair keyPair=keyPairGenerator.generateKeyPair();

            publicKey =(RSAPublicKey) keyPair.getPublic();
            privateKey = (RSAPrivateKey) keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("generate rsa key error");
            e.printStackTrace();
        }
    }

    public String getPublicKey(){
        byte[] publocKeyBytes = publicKey.getEncoded();
        String rtn = Base64.encodeBase64String(publocKeyBytes);
        return rtn;
    }
}
