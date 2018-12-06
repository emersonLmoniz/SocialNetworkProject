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

/* 
 * Controller of the Creating a Chatroom 
 * Retrieves all the information necessary to create the chatroom
 * Written by: Emerson Moniz
 * Date:11/30/2018
 */
public class CreateWindowController {
	// Implement methods in controller class
	@FXML // fx:id="tfChatKey"
	private TextField tfChatKey; // Value injected by FXMLLoader

	@FXML // fx:id="tfUsername"
	private TextField tfUsername; // Value injected by FXMLLoader

	@FXML // fx:id="tfChatName"
	private TextField tfChatName; // Value injected by FXMLLoader

	@FXML // fx:id="taAllowedUserName"
	private TextArea taAllowedUserName; // Value injected by FXMLLoader

	private Object client;

	private static int numUser;
	private static String msgout, msgin;

	@FXML
	void clickStartChatroom(ActionEvent event) throws IOException {
		String username = tfUsername.getText();
		// String chatname = tfChatName.getText();
		String chatkey = tfChatKey.getText();
		String userscanjoin = taAllowedUserName.getText();
		Socket cs = new Socket("10.200.8.184", 8800); // server
		DataOutputStream douts= new DataOutputStream(cs.getOutputStream());
		DataInputStream dins = new DataInputStream(cs.getInputStream());
		//cs = new Socket("10.200.8.184", 8800); // server
		//dins = new DataInputStream(cs.getInputStream());
		//douts = new DataOutputStream(cs.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Client temp = new Client(cs,douts,dins);
			//douts = temp.getDouts();
			//	dins = temp.getDins();
			
			
			numUser = temp.getNumUser();// read the number of users is already created
			System.out.println("op"+numUser);

		if (numUser == 0) { // create chat
			System.out.println("Create a new chat:");
			Client.flushMessages(douts, username, chatkey, userscanjoin);
			// readInput(br); // get All the inputs to create new chat
		} else { // join chat
			chatkey = "X";
			// System.out.println("Enter your user name:");
			// userName = br.readLine();
			douts.writeUTF(username);
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
	}catch(

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
	
	void setClient(Client c) {
		this.client = c;
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
}
