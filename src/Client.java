import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/* 
 * GUI Portion of the Client
 * This GUI interacts with the client 
 * Written by: Emerson Moniz
 * Date: 11/30/2018
 */

/*
* Client Main Function Send and receive message from multiple clients 
* Written by: Alex J. Monteiro De Pina
* Date: 11/29/2018
*/

public class Client extends Application {
	
	private static int numUser;
	private static String  key, listOfpeople, userName, msgout, msgin;
	
	/**
	 * 
	 * @param k receives the key for the client
	 */

	/**
	 * 
	 * @param in takes a scanner to get all the inputs.
	 * @return
	 * @throws IOException
	 */

	public static void readInput(String n, String k, String l) throws IOException {
		/// ---------------------------------------------------------------
		//System.out.println("Enter the user name");
		//userName = br.readLine();
		//System.out.println("Enter Chat key");
		//key = br.readLine();
		//System.out.println("Enter List of people");
		//listOfpeople = br.readLine();
		//System.out.println("Created");
		userName = n;
		key = k;
		listOfpeople = l;
	}
	public static void getUser(String n) {
		userName = n;
	}
	public static void getSendMessage(String m) {
		msgout = m;
	}
	public static void SendMessage(String m) {
		msgout = m;
	}


	/**
	 * Implemented by Alex J. Monteiro De Pina
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
			
			Socket cs = new Socket("localhost", 8800); // server
			System.out.println("Client is Runnig");
			DataInputStream dins = new DataInputStream(cs.getInputStream());
			DataOutputStream douts = new DataOutputStream(cs.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			numUser = dins.readInt();// read the number of users is already created
			if ( numUser == 0) { // create chat 
				System.out.println("Create a new chat:");
				launch(args);
				//readInput(br); // get All the inputs to create new chat
				douts.writeUTF(userName);
				douts.writeUTF(key);
				douts.writeUTF(listOfpeople);
				douts.flush();
			}
			else { // join chat 
				key = "X";
				launch(args);
				//System.out.println("Enter your user name:");
				//userName = br.readLine();	
				douts.writeUTF(userName);
				douts.flush();
				
			}
			
			Thread sendMessage = new Thread(new Runnable() {
				public void run() {
					while (true) {
						
						try {
							msgout = br.readLine();
							douts.writeUTF(msgout);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});
			Thread readMessage = new Thread(new Runnable() {
				public void run() {
					while (true) {
						
						try {
							msgin = dins.readUTF();
							System.out.println(msgin);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});
			sendMessage.start();
			readMessage.start();
			// cs.close();
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
			System.exit(1);
		}
		 
	}

	public void start(Stage primaryStage) throws Exception {
		// Load the Client GUI
		if ( numUser == 0)
		{
		Parent mainWindow = FXMLLoader.load(getClass().getResource("CreateWindowView.fxml"));
		primaryStage.setTitle("Create a new Chat");
		primaryStage.setScene(new Scene(mainWindow, 600, 500));
		primaryStage.show();
		}
		else
		{
			primaryStage = new Stage();
			Parent mainWindow = FXMLLoader.load(getClass().getResource("EnterUserNameView.fxml"));
			primaryStage.setTitle("Join a Chat");
			primaryStage.setScene(new Scene(mainWindow, 300, 120));
			primaryStage.show();
		}
	}
}
