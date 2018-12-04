import java.io.IOException;
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
public class CreateWindowController{
// Implement methods in controller class
    @FXML // fx:id="tfChatKey"
    private TextField tfChatKey; // Value injected by FXMLLoader

    @FXML // fx:id="tfUsername"
    private TextField tfUsername; // Value injected by FXMLLoader

    @FXML // fx:id="tfChatName"
    private TextField tfChatName; // Value injected by FXMLLoader

    @FXML // fx:id="taAllowedUserName"
    private TextArea taAllowedUserName; // Value injected by FXMLLoader

    @FXML
    void clickStartChatroom(ActionEvent event) throws IOException {
		String username = tfUsername.getText();
		String chatname = tfChatName.getText();
		String chatkey = tfChatKey.getText();
		String [] userscanjoin = taAllowedUserName.getText().split("\n");
//    	for (String users : userscanjoin)
//    		System.out.println(users);
		Parent chatRoomParent = FXMLLoader.load(getClass().getResource("ChatRoomView.fxml"));
		Scene chatRoomScene = new Scene(chatRoomParent);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(chatRoomScene);
		window.show();
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
//	public void clickStartChatroom(ActionEvent event) throws IOException{
////		TODO: Create an operation for when the user clicks join chatroom
//		//get data
//		//sending data to server
//		
		
//		System.out.println("User has entered all necessary information, for chatroom");

////		tfUsername.setText("James");
//		String username = tfUsername.getText();
//		String chatname = tfChatName.getText();
//		String chatkey = tfChatKey.getText();
//		String [] userscanjoin = taAllowedUserName.getText().split("\n");
//

//	}
	
//	public void clickPreviousPage(ActionEvent event) throws IOException {
////		TODO: Create an operation for when the user clicks join chatroom
//		System.out.println("User has decided to quit operation");
//		Parent mainWindowParent = FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
//		Scene mainWindowScene = new Scene(mainWindowParent);
//		
//		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
//		window.setScene(mainWindowScene);
//		window.show();
//	}

//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//		
//	}
	
//	TODO: IMPLEMENT ERROR MESSAGES
}
