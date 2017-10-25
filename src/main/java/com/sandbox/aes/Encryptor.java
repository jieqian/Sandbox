package com.sandbox.aes;

import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.sandbox.gson.Gsons;
import com.sandbox.murmurhash3.MurmurHash3;
import com.sandbox.utils.pojo.SDKToken;

public class Encryptor {
    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV
        
        String appKey = "keybe86656dc0a242a6a54b37b70b90718c";
        String deviceId = "123456789012345abcde";
        String str = appKey + deviceId;
        Date now = new Date();
        long timestamp = now.getTime();
        SDKToken sdkToken = new SDKToken();
        sdkToken.setAppkey(appKey);
        sdkToken.setDeviceId(deviceId);
        sdkToken.setTimestamp(timestamp);
        String json = Gsons.DEFAUTL.instance().toJson(sdkToken);
        
//        MurmurHash3.LongPair out = new MurmurHash3.LongPair();
//        MurmurHash3.murmurhash3_x64_128(appKey.getBytes(), 1, 1, 0, out);
//        System.out.println(out.val1);
//        System.out.println(out.val2);
        
        System.out.println(json);
        System.out.println(MurmurHash3.getInstance().hash64(json));

        System.out.println(decrypt(key, initVector,
                encrypt(key, initVector, "Hello World")));
    }
}