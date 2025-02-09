package com.encryption.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
public class AESUtil {
	

	 
	    private static final String SECRET_KEY = "1234567890123456"; 
	    private static final String ALGORITHM = "AES";

	    public static String encrypt(String data) throws Exception {
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
	        byte[] encrypted = cipher.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(encrypted);
	    }

	    public static String decrypt(String encryptedData) throws Exception {
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, keySpec);
	        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
	        return new String(decrypted);
	    }
	    public static void main(String args[]) {
	    	try {
				String s=encrypt("{\"name\":\"chandaben\",\"email\":\"chandaben.doe@example.com\"}");
				System.out.println("Encrypted=======>"+s);
				System.out.println("id======>"+encrypt("2"));
				String s1= decrypt("6qrpydQ0KG/NHZbkhmH12D+tsxyAu5mhYL1ImqvHaTNTnMH72+TKozKgWmqmkNiUq8PlksZYtqu+kwudWi+VOfOt3nIpWnCNXdGiN4TKudjrcQDXkzdGbgNRIoDZ7w+LFO2Ua8QexhagLEBDqSlRrSadWh/oQP242knhqXpfnfsoAbGUaTN7tnCIRZ474G0e/4i33sotYxRue4Wzzxf9yO9gSn8S8/TjIaiLh1CyW96DRIbMaZpu9cnUX0c5M2+gK4MP7PV7VxneFJManB38KeBEs7CO4ZD2XNRjOp/nnHJXEXOxwReDqVIYm5aqHMu7Jp1aH+hA/bjaSeGpel+d+/otlMDxu4NOo1dTZtUt17A=");
				System.out.println("Decrypted=======>"+ s1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }

}
