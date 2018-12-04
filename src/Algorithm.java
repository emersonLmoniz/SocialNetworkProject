/* Algorithm to encrypt and decrypt the messages
 * Written by: Emerson Moniz
 * Date: 12/3/2018
 */

public class Algorithm {
	/*
	 * First loop used to find the ascii key value of the key then shift the values to its 9x 
	 * After this is implemented you will store that value in the keyValues array of characters 
	 * Second loop will be used to create the encrypted message
	 * This method returns the encrypted message 
	 */
	public static String encrypt(String message,String key) {
		String encryptedMessage="";
//		Used to store the random values for the encryption 
		char keyValues[] = new char[key.length()];
//		Keyvalue counter will be used in case someone has a message larger than the current index of the word
		int keyValueCounter = 0;
//		
		for (int i =0;i<key.length();i++) {
			int characterIndex = 0;
			characterIndex = (int)(key.charAt(i));
			characterIndex *= 9;
			keyValues[i]=(char) characterIndex;
		}
		
		int lengthOfMessage = message.length();
		
		for(int i=0; i < lengthOfMessage;i++) {
			if (keyValueCounter >= keyValues.length -1) keyValueCounter=0;
			int characterIndex = 0;
			characterIndex = (int)(message.charAt(i));
			characterIndex += keyValues[keyValueCounter];
			encryptedMessage += (char)characterIndex;
			keyValueCounter++;
		}
		
		return encryptedMessage;
	}
	/*
	 * First loop used to find the ascii key value of the key then shift the values to its 9x 
	 * After this is implemented you will store that value in the keyValues array of characters 
	 * Second loop will be used to create the encrypted message
	 * This method returns the encrypted message 
	 */
	private static String decrypt(String encryptedMessage,String key) {
		String decryptedMessage="";
//		Used to store the random values for the decryption 
		int keyValues[] = new int[key.length()];
//		Keyvalue counter will be used in case someone has a message larger than the current index of the word
		int keyValueCounter = 0;
//		
		for (int i =0;i<key.length();i++) {
			int characterIndex = 0;
			characterIndex = (int)(key.charAt(i));
			characterIndex *= 9;
			keyValues[i]=characterIndex;
		}
		
		int lengthOfMessage = encryptedMessage.length();
		
		for(int i=0; i < lengthOfMessage;i++) {
			if (keyValueCounter >= keyValues.length -1) keyValueCounter=0;
			int characterIndex = 0;
			characterIndex = (encryptedMessage.charAt(i));
			characterIndex -= keyValues[keyValueCounter];
			decryptedMessage += (char)characterIndex;
			keyValueCounter++;
		}
		
		return decryptedMessage;
	}
}
