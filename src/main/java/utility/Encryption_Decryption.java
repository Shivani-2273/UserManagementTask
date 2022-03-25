package utility;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class Encryption_Decryption {
	public static String algorithm="DESede";
	
	public static byte[] encrypt(String input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		Key key= KeyGenerator.getInstance("DESede").generateKey();
		Cipher cipher=Cipher.getInstance(algorithm); 
		cipher.init(Cipher.ENCRYPT_MODE,key);
		byte[] inputBytes=input.getBytes();
		return cipher.doFinal(inputBytes);
		
	}
	
	
	public static String decrypt(byte[] encryptionBytes) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		Key key= KeyGenerator.getInstance("DES").generateKey();
		Cipher cipher=Cipher.getInstance(algorithm); 
		cipher.init(Cipher.DECRYPT_MODE,key);
		byte[] recoveredBytes=cipher.doFinal(encryptionBytes);
		String recovered=new String(recoveredBytes);
		return recovered;
	
	}
	

}
