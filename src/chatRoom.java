import java.io.IOException;
import java.util.ArrayList;

public class chatRoom {
	private int socket; // will be used to create a new server socket
	private String chatName; // chat name.
	private ArrayList<String> allowedUsers;
	private String key; // for decription
	/**
	 * @param s The Socket Number for the chat
	 * @param n The name for the chat
	 * @throws IOException
	 */
	public chatRoom(int s, String k, String [] au) throws IOException {
		socket = s;
		key = k;
		for(int i = 0; i < au.length; i++) { // convert String Array to Arraylist
			allowedUsers.add(au[i]); 
		}
	}

	/**
	 * @return the socket number for this current chat
	 */
	public int getSocketNum() {
		return socket;
	}

	/**
	 * @return The chat name for this current chat
	 */
	public String getChatName() {
		return chatName;
	}

	/**
	 * @return The server socket that is being used for this chat
	 */
	public String getKey() {
		return key;
	}

}
