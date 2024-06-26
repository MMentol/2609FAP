package model;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.*;

public class Crypto {
    
    public static String encrypt(String strToEncrypt, byte[] key, String cip) {
	String encryptedString = null;
	try {                  
            Cipher cipher = Cipher.getInstance(cip);
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
	}
        catch (Exception e) {
            System.err.println(e.getMessage());
	}
	
        return encryptedString;
    }

    public static String decrypt(String codeDecrypt, byte[] key, String cip){
        String decryptedString = null;
	try {
            Cipher cipher = Cipher.getInstance(cip);
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedString = new String(cipher.doFinal(Base64.decodeBase64(codeDecrypt)));
	}
        catch (Exception e) {
            System.err.println(e.getMessage());
	}
	
        return decryptedString.trim();
    }	

}
