//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import java.net.*;
////import java.util.ArrayList;
////import java.util.Scanner;
//import java.io.*;
//
///* 
// * GUI Portion of the Client
// * This GUI interacts with the client 
// * Written by: Emerson Moniz
// * Date: 11/30/2018
// */
//
///*
//* Client Main Function Send and receive message from multiple clients 
//* Written by: Alex J. Monteiro De Pina
//* Date: 11/29/2018
//*/
//
//public class Client extends Application {
//	
//	private static int numUser;
//	static Socket cs;
//	public static DataOutputStream douts;
//	public static DataInputStream dins;
////	= new DataOutputStream(cs.getOutputStream());
//	//private static String  key, listOfpeople, userName, msgout, msgin;
//
//	/**
//	 * 
//	 * @param k receives the key for the client
//	 */
//
//	/**
//	 * 
//	 * @param in takes a scanner to get all the inputs.
//	 * @return
//	 * @throws IOException
//	 */
//
//	Client (Socket clientS, DataOutputStream outs, DataInputStream ins){
//		douts = outs;
//		dins = ins;
//		cs = clientS;
//	}
//	/*public static void readInput(String n, String k, String l) throws IOException {
//		/// ---------------------------------------------------------------
//		//System.out.println("Enter the user name");
//		//userName = br.readLine();
//		//System.out.println("Enter Chat key");
//		//key = br.readLine();
//		//System.out.println("Enter List of people");
//		//listOfpeople = br.readLine();
//		//System.out.println("Created");
//		userName = n;
//		key = k;
//		listOfpeople = l;
//	}*/
//	public static void flushMessages(DataOutputStream douts, String username, String k, String lpeople) throws IOException {
//		douts.writeUTF(username);
//		douts.writeUTF(k);
//		douts.writeUTF(lpeople);
//		douts.flush();
//	}
//	/**
//	 * 
//	 * @return
//	 */
//	public static DataOutputStream getDouts() {
//		return douts;
//	}
//
//	/**
//	 * @return dins
//	 */
//	/*public static DataInputStream getDins() {
//		return dins;
//	}
//
//	public static void setDouts(DataOutputStream outputStream) {
//		douts = outputStream;
//	}*/
//	/**
//	 * @param dins the dins to set
//	 */
//	public static void setDins(DataInputStream inputStream) {
//		dins = inputStream;
//	}
//
//	public int getNumUser() throws IOException {
//		numUser = dins.readInt();
//		return numUser;
//	}
//	/*public static void getSendMessage(String m) {
//		msgout = m;
//	}
//	*/
//
//
//	/**
//	 * Implemented by Alex J. Monteiro De Pina
//	 * 
//	 * @param args
//	 * @throws IOException
//	 */
//	public static void main(String[] args) throws IOException {
////     	numUser = Server.getNumUser();
//		//InetAddress addr = InetAddress."10.200.188.195";
//		cs = new Socket("10.200.8.184", 8800); // server
//		dins = new DataInputStream(cs.getInputStream());
//		douts = new DataOutputStream(cs.getOutputStream());
//	//	setDouts(douts);
//		//setDins(dins);
//		//getNumUser();
//		
//		//Client c = new Client();
//	
//		System.out.println("Client is Runnig");
//		launch(args);
//		
//					 
//	}
//
//	public void start(Stage primaryStage) throws Exception {
//		// Load the Client GUI
//		//CreateWindowController temp 
//		if (numUser == 0)
//		{
//		Parent mainWindow = FXMLLoader.load(getClass().getResource("CreateWindowView.fxml"));
//		primaryStage.setTitle("Create a new Chat");
//		primaryStage.setScene(new Scene(mainWindow, 600, 500));
//		primaryStage.show();
//		}
//		else
//		{
//			Stage SecondStage = new Stage();
//			Parent mainWindow = FXMLLoader.load(getClass().getResource("EnterUserNameView.fxml"));
//			SecondStage.setTitle("Join a Chat");
//			SecondStage.setScene(new Scene(mainWindow, 300, 120));
//			SecondStage.show();
//		}
//	}
//}
