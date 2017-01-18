package com.ld.web.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 
 * <p>Title: DESUtil </p>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p>Description: DES加密工具类 </p>
 * 
 * @author LD
 * 
 * @date 2015-08-26
 */
public class EncryptionUtil {

    private static final Logger logger = Logger.getLogger(EncryptionUtil.class);

    private static final String MD5 = "MD5";
    private static final String SHA256 = "SHA-256";
    private static final String HMAC_SHA1 = "HmacSHA1";

    public static final String ALGORITHM_DES = "DES";
    public static final String ALGORITHM_DESEDE = "DESede";
    public static final String DES_CIPHER_ALGORITHM_CBC = "DES/CBC/PKCS5Padding";
    public static final String DESEDE_CIPHER_ALGORITHM_CBC = "DESede/CBC/PKCS5Padding";

    /**
     * Md5
     * 
     * @param source
     * @return
     * @throws Exception
     */
    public static String md5EncryptOutHex(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            String md5 = "";
            for (byte b : md.digest(source.getBytes())) {
                String temp = Integer.toHexString(b & 0xff);
                md5 += (temp.length() == 1 ? "0" + temp : temp);
            }
            return md5;
        } catch (Exception e) {
            // This should not happen!
            logger.error(String.format("MD5 encryptOutHex error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Sha256 to encryption
     * 
     * @param input
     * @return
     */
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA256);
            md.update(input.getBytes("UTF-8"));
            byte[] data = md.digest();
            StringBuffer result = new StringBuffer(data.length * 2);
            for (int i = 0; i < data.length; i++) {
                result.append(Integer.toHexString(data[i] & 0xff));
            }
            return result.toString();
        } catch (Exception e) {
            logger.error(String.format("SHA256 to encryption error: %s", e.getMessage()), e);
            // This should not happen!
            throw new RuntimeException(e);
        }
    }

    /**
     * HmacSHA1
     * 
     * @param source
     * @param key
     * @return
     * @throws Exception
     */
    public static String hmacSHA1EncryptOutHex(String source, String key, String chanset) {
        try {
            chanset = StringUtil.isEmpty(chanset) ? "utf-8" : chanset;

            Mac mac = Mac.getInstance(HMAC_SHA1);
            mac.init(new SecretKeySpec(key.getBytes(chanset), HMAC_SHA1));

            return toHex(mac.doFinal(source.getBytes(chanset))).toUpperCase();
        } catch (Exception e) {
            // This should not happen!
            logger.error(String.format("HmacSHA1 encryptOutHex error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    public static String desEncryptOutHex(String algorithm, String cipherAlgorithm, String source, byte[] key, String iv, String charset) {
        try {
            charset = StringUtil.isEmpty(charset) ? "utf-8" : charset;

            Cipher cipher = Cipher.getInstance(cipherAlgorithm);

            int size = cipher.getBlockSize();
            byte[] ivBytes = new byte[size];

            if (StringUtil.isEmpty(iv)) {
                for (int i = 0; i < size; ++i) {
                    ivBytes[i] = 0;
                }
            } else {
                ivBytes = iv.getBytes(charset);
            }

            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, algorithm), ivSpec);

            return Base64.encodeBase64String(cipher.doFinal(source.getBytes(charset)));
        } catch (Exception e) {
            // This should not happen!
            logger.error(String.format("DES EncryptOutHex error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    public static String desDecryptOutHex(String algorithm, String cipherAlgorithm, String source, byte[] key, String iv, String charset) {
        try {
            charset = StringUtil.isEmpty(charset) ? "utf-8" : charset;

            Cipher cipher = Cipher.getInstance(cipherAlgorithm);

            int size = cipher.getBlockSize();
            byte[] ivBytes = new byte[size];

            if (StringUtil.isEmpty(iv)) {
                for (int i = 0; i < size; ++i) {
                    ivBytes[i] = 0;
                }
            } else {
                ivBytes = iv.getBytes(charset);
            }

            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, algorithm), ivSpec);

            String result = new String(cipher.doFinal(Base64.decodeBase64(source)), charset);
            logger.info(String.format("Des decrypt data: %s", result));

            return result;
        } catch (Exception e) {
            // This should not happen!
            logger.error(String.format("DES DecryptOutHex error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Byte转换为16进制数据
     * 
     * @param bytes
     * @return
     */
    public static String toHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("ToHex encrypt data is null...");
        }
        String value = "", temp = "";
        for (byte b : bytes) {
            temp = Integer.toHexString(b & 0xff);
            value += temp.length() == 1 ? "0" + temp : temp;
        }
        return value;
    }

}
