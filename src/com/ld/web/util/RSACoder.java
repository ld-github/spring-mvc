package com.ld.web.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * <p>Title: RSACoder</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:RSA加密解密工具类</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
public class RSACoder {

    private static final String RSA = "RSA";

    public static final String MD5_WITH_RSA = "MD5withRSA";
    public static final String SHA1_WITH_RSA = "SHA1WithRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 用私钥对信息生成数字签名
     * 
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String sign(String algorithm, byte[] data, String privateKey) throws Exception {
        algorithm = StringUtil.isEmpty(algorithm) ? MD5_WITH_RSA : algorithm;

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));

        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey priKey = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(priKey);
        signature.update(data);

        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * 校验数字签名
     * 
     * @param data
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     * 
     */
    public static boolean verify(String algorithm, byte[] data, String publicKey, String sign) throws Exception {
        algorithm = StringUtil.isEmpty(algorithm) ? MD5_WITH_RSA : algorithm;

        byte[] keyBytes = Base64.decodeBase64(publicKey);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(pubKey);
        signature.update(data);

        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * 用私钥解密
     * 
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key key = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥解密
     * 
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key key = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥加密
     * 
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key key = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    /**
     * 用私钥加密
     * 
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key key = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     * 
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 取得公钥
     * 
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * * 初始化密钥
     * 
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
}
