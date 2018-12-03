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

	private static LinkedList<chatRoom> chatRooms = new LinkedList<>();
	private static int numOfChats = 0;
	static Vector<ClientHandler> users = new Vector<>();
	private static int portNumber = 8800; // this number will be used to create a new chat room using the port
	/**
	 * WIll be used to diplay all available chats on the GUI
	 */
	private static void displayChats() {
		// NEED TO Be Implemented based on what GUI is doing
	}
	/**
	 * Implemented by Alex J. Monteiro De Pina
	 * @param in takes a scanner that will used for user input will be replaced with GUI
	 * @throws IOException
	 */
	private static void newChat(Scanner in) throws IOException {
		System.out.println("Enter Chat Name: ");
		String chatName = in.next();
		System.out.println("Enter Chat key: ");
		int key = in.nextInt();
		
		chatRooms.add(new chatRoom(portNumber,key, chatName));
		numOfChats++;
		portNumber = portNumber + 100;// increase the port number for the next chat
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**
		 * TEMPORARY use of command line all the input will be replaced with the GUI input
		 */
		System.out.println("Do you want to Join or Create a new Chat");
		System.out.println("(1) Join\n(2)Create");
		Scanner in = new Scanner(System.in);
		///------------------------------------------------------------------
		int option = in.nextInt(); // Will be used to determine if user is creating a new chat or join and existing one 
		
		String displayMsg = "";
		try {
			switch(option) {
			case 1: // Join and Existing chat
				if (numOfChats == 0)
					displayMsg = "No available chat to join!";
				else {
					displayChats(); // Display all available chat
					String userName = in.next();
					}
				break;
			case 2: // create a new chat 
				newChat(in);
				break;
			default:
				displayMsg = "Something went wrong. Either Join or Create a Chat.";
				System.out.println(displayMsg);
				break;
				}
			
			
		
			chatRoom test = new chatRoom(8800,0, "chatAlex");
			ServerSocket ss = test.getServerSocket();
			System.out.println("Server is Runnig");
			Socket s;
			int i = 0;

			while (true) {
				s = ss.accept();
				System.out.println("New client request received for chat: " + test.getChatName());
				// set up streams
				DataInputStream dins = new DataInputStream(s.getInputStream());
				DataOutputStream douts = new DataOutputStream(s.getOutputStream());
				
				
				// Create a new handler for this client
				
				ClientHandler user = new ClientHandler(s,"client " +i, dins, douts);
				i++; // increment user number
				Thread t = new Thread(user);// creates a new thread for this user
				users.add(user);
				t.start();
				
			}
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
			System.exit(1);
		}

	}

	/* 
	 * chatRoom Class
	 * Will Create a new chatRoom and hold all the info for the chat 
	 * Written by: Alex J. Monteiro De Pina 
	 * Date: 11/29/2018
	 */

	private static class chatRoom {
		private int socket; // will be used to create a new server socket
		private String chatName; // chat name.
		private ServerSocket chatSocket; // array that will store all the server sockets
		private ArrayList<String> allowedUsers;
		private int key; // for decription
		/**
		 * @param s The Socket Number for the chat
		 * @param n The name for the chat
		 * @throws IOException
		 */
		public chatRoom(int s, int k, String n) throws IOException {
			socket = s;
			chatName = n;
			key = k;
			chatSocket = new ServerSocket(socket);
			allowedUsers = new ArrayList<>();
			numOfChats++;
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
		public ServerSocket getServerSocket() {
			return chatSocket;
		}
		public void addGuest(String g) {
			allowedUsers.add(g);
		}
		public int getKey() {
			return key;
		}

	}
}

/* 
 * Client Handler 
 * A helper class that will will handle multiple clients in the same chatroom 
 * manage the send and receive of the message
 * Written by: Alex J. Monteiro De Pina 
 * Date: 11/29/2018
 */
class ClientHandler implements Runnable {
	
	private String name;
	final DataInputStream dins;
	final DataOutputStream douts;
	Socket s;
	boolean isloggedin;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// constructor
	public ClientHandler(Socket s, String name, DataInputStream dins, 
			DataOutputStream douts) {
		this.dins = dins;
		this.douts = douts;
		this.name = name;
		this.s = s;
		this.isloggedin = true;
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
				for (ClientHandler ch : Server.users) { // send the message to all the users
					if ((!(ch.name).equals(this.name)) && (ch.isloggedin==true)) {
					ch.douts.writeUTF(this.name+": "+msgout);
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
