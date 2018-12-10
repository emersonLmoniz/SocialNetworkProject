package Controllers;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* 
 * Controller of the Main Window for Client 
 * User flow includes either creating or joining chatroom
 * Written by: Emerson Moniz
 * Date:11/30/2018
 */
public class MainWindowController {
	
	public void clickJoinChatroom(ActionEvent event) throws IOException {
//		TODO: Create an operation for when the user clicks join chatroom
		System.out.println("User clicked join");
		Parent joinWindowParent = FXMLLoader.load(getClass().getResource("JoinWindowView.fxml"));
		Scene joinWindowScene = new Scene(joinWindowParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(joinWindowScene);
		window.show();
	}
	
	public void clickCreateChatroom(ActionEvent event) throws IOException {
//		TODO: Create an operation for when the user clicks join chatroom
		System.out.println("User clicked create");
		Parent createWindowParent = FXMLLoader.load(getClass().getResource("CreateWindowView.fxml"));
		Scene createWindowScene = new Scene(createWindowParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(createWindowScene);
		window.show();
	}
}
