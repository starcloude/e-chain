package com.echain.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder; 

@SuppressWarnings("restriction")
public class AesEncodeUtil {
  
    //编码方式
    private static final String CODE_TYPE = "UTF-8";
    
    //填充类型
    private static final String AES_TYPE = "AES/ECB/PKCS5Padding";
      
    //私钥  
    private static final String AES_KEY="ILikeEchain";
    
	//私钥 -VUE专用
    private static final String VUE_KEY="VueKeyOnly";
    
    /**
     * 加密
     * @param cleartext
     * @return
     */
    public static String encrypt(String cleartext) {
    	return encrypt(cleartext, AES_KEY);
    }
    
    /**
     * 解密
     * @param encrypted
     * @return
     */
    public static String decrypt(String encrypted) {
    	return decrypt(encrypted, AES_KEY);
    }
    
    /**
     *	 加密 
     * @param cleartext 
     * @return 
     */  
	public static String encrypt(String cleartext,String aesKey) {
    	if(StringUtils.isBlank(cleartext)) {
    		return null;
    	}
        try {  
            Cipher cipher = Cipher.getInstance(AES_TYPE);  
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(aesKey));
            byte[] encryptedData = cipher.doFinal(cleartext.getBytes(CODE_TYPE));
            return new BASE64Encoder().encode(encryptedData);  
        } catch (Exception e) {
            return null;
        }  
    }  
  
    /** 
     * 解密 
     *  
     * @param encrypted 
     * @return 
     */  
	public static String decrypt(String encrypted,String aesKey) {
    	if(StringUtils.isBlank(encrypted)) {
    		return null;
    	}
        try {
            byte[] byteMi = new BASE64Decoder().decodeBuffer(encrypted);  
            Cipher cipher = Cipher.getInstance(AES_TYPE);  
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(aesKey));
            byte[] decryptedData = cipher.doFinal(byteMi);  
            return new String(decryptedData, CODE_TYPE);
        } catch (Exception e) {
            return null;  
        }
    }
    

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
	        kg = KeyGenerator.getInstance("AES");
	        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
	        secureRandom.setSeed(password.getBytes());
	        //AES 要求密钥长度为 128
//	        kg.init(128, new SecureRandom(password.getBytes()));
	        kg.init(128, secureRandom);
	        //生成一个密钥
	        SecretKey secretKey = kg.generateKey();
	        return new SecretKeySpec(secretKey.getEncoded(), "AES");// 转换为AES专用密钥
        }catch(Exception ex) {
        	return null;
        }
    }
    
    /**
     *	 加密 - VUE专用
     * @param cleartext 
     * @return 
     */  
	public static String vue_ciphertext(String cleartext) {
		return encrypt(cleartext, VUE_KEY);
    }  
  
    /** 
     * 解密  - VUE专用
     *  
     * @param encrypted 
     * @return 
     */  
	public static String vue_plaintext(String encrypted) {
		return decrypt(encrypted, VUE_KEY);
    }
}