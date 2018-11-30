import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

public class Server {

	private static LinkedList<chatRoom> chatRooms;
	private static int numOfChats = 0;
	static Vector<ClientHandler> users = new Vector<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			chatRoom test = new chatRoom(8800, "chatAlex");
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

	/**
	 * 
	 * @author depinaa5
	 *
	 */
	private static class chatRoom {
		private int socket; // will be used to create a new server socket
		private String chatName; // chat name.
		private ServerSocket chatSocket; // array that will store all the server sockets
		
		/**
		 * @param s The Socket Number for the chat
		 * @param n The name for the chat
		 * @throws IOException
		 */
		public chatRoom(int s, String n) throws IOException {
			socket = s;
			chatName = n;
			chatSocket = new ServerSocket(socket);
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

	}
}

//ClientHandler class 
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
					ch.douts.writeUTF(ch.name+": "+msgout);
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
