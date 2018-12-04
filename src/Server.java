import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
/* 
 * Server Class 
 * Written by: Alex J. Monteiro De Pina 
 * Date: 11/30/2018
 */

public class Server {
	private static String userName, key, allowedU;
	static String[] allowedUsers;
	static ArrayList<String> allowedUList = new ArrayList<>();
	static Vector<ClientHandler> users = new Vector<>();
	static chatRoom chat;
	
	public static int getNumUser() {
		return users.size();
	}
	public static void arrayToList() {
		for(int i = 0; i <  allowedUsers.length; i++) { // convert String Array to Arraylist
			allowedUList.add(allowedUsers[i]); 
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[]args) {

		try {
			System.out.println("Server is Running");
			ServerSocket ss = new ServerSocket(8800);
			
			while (true) {
					
					Socket s =ss.accept();
					System.out.println("New client request received for chat.");
					
					// set up streams
					DataInputStream dins = new DataInputStream(s.getInputStream());
					DataOutputStream douts = new DataOutputStream(s.getOutputStream());
					douts.writeInt(getNumUser());
					douts.flush();
					if (getNumUser()==0) {
						userName = dins.readUTF();
						key = dins.readUTF();
						allowedU = dins.readUTF();
						allowedUsers = allowedU.split(" ");
						chat = new chatRoom(8800, key, allowedUsers);
					}
					else {
						userName = dins.readUTF();
					}
					// Create a new handler for this client

					ClientHandler user = new ClientHandler(s,userName, dins, douts);
					Thread t = new Thread(user);// creates a new thread for this user
					users.add(user);
					t.start();	
			}
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
			System.exit(1);
		}

	}
}

/*
 * chatRoom Class Will Create a new chatRoom and hold all the info for the chat
 * Written by: Alex J. Monteiro De Pina Date: 11/29/2018
 */

/*
 * Client Handler A helper class that will will handle multiple clients in the
 * same chatroom manage the send and receive of the message Written by: Alex J.
 * Monteiro De Pina Date: 11/29/2018
 */
class ClientHandler implements Runnable {

	private String name;
	final DataInputStream dins;
	final DataOutputStream douts;
	boolean isloggedin;
	Socket s;
	private static String key;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// constructor
	public ClientHandler(Socket s,String name, DataInputStream dins, DataOutputStream douts) {
		this.dins = dins;
		this.douts = douts;
		this.name = name;
		this.s = s;
		this.isloggedin = true;
	}
	private static void destributeKey() {
		for (ClientHandler ch : Server.users) {
			if (Server.allowedUList.contains(ch.name)) {
				key = Server.chat.getKey();
			}
			else
				key = " ";
		}
	}

	@Override
	public void run() {
		String msgin = "", msgout = "";
		while (true) {
			try {
				// receive the string
				msgin = dins.readUTF();
				if (msgin.equals("exit")) { // user wants to leave chat
					isloggedin = false;
					this.s.close();
					break;
				}
				msgout = msgin;
				destributeKey();
				for (ClientHandler ch : Server.users) { // send the message to all the users
					if ((!(ch.name).equals(this.name)) && (ch.isloggedin == true)) {
						msgout = Algorithm.decrypt(msgout,ch.key);
						ch.douts.writeUTF(this.name + ": " + msgout);
						ch.douts.flush();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			// closing resources
			this.dins.close();
			this.douts.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
