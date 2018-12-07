import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Application;

/* 
 * Controller of the Creating a Chatroom 
 * Retrieves all the information necessary to create the chatroom
 * Written by: Emerson Moniz
 * Date:11/30/2018
 */
public class CreateWindowController extends Application {
	// Implement methods in controller class
	@FXML // fx:id="tfChatKey"
	private TextField tfChatKey; // Value injected by FXMLLoader

	@FXML // fx:id="tfUsername"
	private TextField tfUsername; // Value injected by FXMLLoader

	@FXML // fx:id="tfChatName"
	private TextField tfChatName; // Value injected by FXMLLoader

	@FXML // fx:id="taAllowedUserName"
	private TextArea taAllowedUserName; // Value injected by FXMLLoader

	

	private static int numUser;
	private static String msgout, msgin;
	
	static Socket cs;
	public static DataOutputStream douts;
	public static DataInputStream dins;
	
	public static void flushMessages(DataOutputStream douts, String username, String k, String lpeople) throws IOException {
		douts.writeUTF(username);
		douts.writeUTF(k);
		douts.writeUTF(lpeople);
		douts.flush();
	}

	@FXML
	void clickStartChatroom(ActionEvent event) throws IOException {
		String username = tfUsername.getText();
		// String chatname = tfChatName.getText();
		String chatkey = tfChatKey.getText();
		String userscanjoin = taAllowedUserName.getText();
//		Socket cs = new Socket("10.200.8.184", 8800); // server
//		DataOutputStream douts= new DataOutputStream(cs.getOutputStream());
//		DataInputStream dins = new DataInputStream(cs.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			//Client temp = new Client(cs,douts,dins);
			//douts = temp.getDouts();
			//	dins = temp.getDins();
			
			System.out.println("op"+numUser);

		if (numUser == 0) { // create chat
			System.out.println("Create a new chat:");
			flushMessages(douts, username, chatkey, userscanjoin);
			
		} else { // join chat
			chatkey = "X";
			douts.writeUTF(username);
			douts.flush();

		}

		Thread sendMessage = new Thread(new Runnable() {
			public void run() {
				while (true) {

					try {
						msgout = br.readLine();
						msgout = Algorithm.encrypt(msgin, Server.chat.getKey()); // encrypt the message before sending it to the server
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
		
	}
	
	catch(

	Exception e)
	{
		System.out.println("\n" + e.getMessage());
		System.exit(1);
	}

	//Client.readInput(username,chatkey,userscanjoin);
	//Client.flushMessages(douts,username,chatkey,userscanjoin);
	// for (String users : userscanjoin)
	// System.out.println(users);
	Parent chatRoomParent = FXMLLoader.load(getClass().getResource("ChatRoomView.fxml"));
	Scene chatRoomScene = new Scene(chatRoomParent);
	Stage window = (Stage) (((Node) event.getSource()).getScene()
			.getWindow());window.setScene(chatRoomScene);window.show();
	}

	@FXML
	void clickPreviousPage(ActionEvent event) throws IOException {
		System.out.println("User has decided to quit operation");
		Parent mainWindowParent = FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
		Scene mainWindowScene = new Scene(mainWindowParent);

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(mainWindowScene);
		window.show();
	}
	
	// public void clickStartChatroom(ActionEvent event) throws IOException{
	//// TODO: Create an operation for when the user clicks join chatroom
	// //get data
	// //sending data to server
	//

	// System.out.println("User has entered all necessary information, for
	// chatroom");

	//// tfUsername.setText("James");
	// String username = tfUsername.getText();
	// String chatname = tfChatName.getText();
	// String chatkey = tfChatKey.getText();
	// String [] userscanjoin = taAllowedUserName.getText().split("\n");
	//

	// }

	// public void clickPreviousPage(ActionEvent event) throws IOException {
	//// TODO: Create an operation for when the user clicks join chatroom
	// System.out.println("User has decided to quit operation");
	// Parent mainWindowParent =
	// FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
	// Scene mainWindowScene = new Scene(mainWindowParent);
	//
	// Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
	// window.setScene(mainWindowScene);
	// window.show();
	// }

	// @Override
	// public void initialize(URL arg0, ResourceBundle arg1) {
	// // TODO Auto-generated method stub
	//
	// }

	// TODO: IMPLEMENT ERROR MESSAGES

public static void main(String[] args) throws IOException {
	// numUser = Server.getNumUser();
	//InetAddress addr = InetAddress."10.200.188.195";
	
	cs = new Socket("localHost", 8800); // server
	dins = new DataInputStream(cs.getInputStream());
	douts = new DataOutputStream(cs.getOutputStream());
	numUser = dins.readInt();// read the number of users is already created
	//setDouts(douts);
	//setDins(dins);
	//getNumUser();
	
	//Client c = new Client();

	System.out.println("Client is Running");
	launch();
	
	
				 
}

	public void start(Stage primaryStage) throws Exception {
		// Load the Client GUI
		// CreateWindowController temp
		// if (numUser == 0)
		// {
		Parent mainWindow = FXMLLoader.load(getClass().getResource("CreateWindowView.fxml"));
		primaryStage.setTitle("Create a new Chat");
		primaryStage.setScene(new Scene(mainWindow, 600, 500));
		primaryStage.show();
		// }
		// else
		// {
		// //Stage SecondStage = new Stage();
		// Parent mainWindow =
		// FXMLLoader.load(getClass().getResource("EnterUserNameView.fxml"));
		// primaryStage.setTitle("Join a Chat");
		// primaryStage.setScene(new Scene(mainWindow, 300, 120));
		// primaryStage.show();
		//
		//
		// }
	}
}
