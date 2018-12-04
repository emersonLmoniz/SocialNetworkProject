import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/* 
 * Controller of the Creating a Chatroom 
 * Sends the username to the list of corresponding server 
 * * Might combine with Join window *
 * Written by: Emerson Moniz
 * Date:11/30/2018
 */
public class EnterUserNameController {
	private TextField fxid;
	public void clickStartChatroom(ActionEvent event) throws IOException {
//		TODO: Create an operation for when the user clicks join chatroom
		//saving data
		String newusername = fxid.getText();
		System.out.println("User has joined the chatroom");
		Parent chatRoomParent = FXMLLoader.load(getClass().getResource("ChatRoomView.fxml"));
		Scene chatRoomScene = new Scene(chatRoomParent);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(chatRoomScene);
		window.show();
	}
	
}
