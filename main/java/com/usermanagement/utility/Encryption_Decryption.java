package com.usermanagement.utility;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encryption_Decryption {
	//algorithm
	public final static String algorithm="AES";
	
	//key to encrypt the password
	public final  static String strkey="B?E(H+MbPeShVmYq";
	static SecretKey key=new SecretKeySpec(strkey.getBytes(),algorithm);
	
	//method to encrypt user password while register and login
	public static byte[] encrypt(String input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
		Cipher cipher=Cipher.getInstance(algorithm); 
		cipher.init(Cipher.ENCRYPT_MODE,key);
		byte[] inputBytes=input.getBytes();
		return cipher.doFinal(inputBytes);	
	}
	
	//method to decrypt password
	public static String decrypt(byte[] encryptionBytes) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher=Cipher.getInstance(algorithm); 
		cipher.init(Cipher.DECRYPT_MODE,key);
		byte[] recoveredBytes=cipher.doFinal(encryptionBytes);
		String recovered=new String(recoveredBytes);
		return recovered;
	
	}
	
	

}
