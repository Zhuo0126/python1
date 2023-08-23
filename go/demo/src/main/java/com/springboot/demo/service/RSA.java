package com.springboot.demo.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.management.openmbean.InvalidKeyException;
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

    public String decrypt(String str){
        //首先Base64解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str);
        String outStr=null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            outStr = new String(cipher.doFinal(inputByte));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();//错误处理，可以根据对应的异常类型做不同的处理。
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException | java.security.InvalidKeyException e) {
            e.printStackTrace();
        }
        return outStr;
    }
}
