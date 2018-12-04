import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	private TextField tfUsername;
	private TextField tfChatName;
	private TextField tfChatKey;
	public void clickStartChatroom(ActionEvent event) throws IOException {
//		TODO: Create an operation for when the user clicks join chatroom
		System.out.println("User has entered all necessary information, for chatroom");
		Parent chatRoomParent = FXMLLoader.load(getClass().getResource("ChatRoomView.fxml"));
		Scene chatRoomScene = new Scene(chatRoomParent);
		String username = tfUsername.getText();
		String chatname = tfChatName.getText();
		String chatkey = tfChatKey.getText();
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(chatRoomScene);
		window.show();
	}
	
	public void clickPreviousPage(ActionEvent event) throws IOException {
//		TODO: Create an operation for when the user clicks join chatroom
		System.out.println("User has decided to quit operation");
		Parent mainWindowParent = FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
		Scene mainWindowScene = new Scene(mainWindowParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(mainWindowScene);
		window.show();
	}
	
//	TODO: IMPLEMENT ERROR MESSAGES
}
