import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
/* 
 * Controller of the Join Chatroom 
 * User will input the username and check if value in the list provided by server 
 * Written by: Emerson Moniz
 * Date:11/30/2018
 */
public class JoinWindowController {
		
	public void clickPrevious(ActionEvent event) throws IOException {
//		TODO: Create an operation for when the user clicks join chatroom
		System.out.println("User clicked previous");
		Parent mainWindowParent = FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
		Scene mainWindowScene = new Scene(mainWindowParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(mainWindowScene);
		window.show();
	}
	public void clickNext(ActionEvent event) throws IOException {
//		TODO: Create an operation for when the user clicks join chatroom
		System.out.println("User clicked next");
		Parent joinWindowParent = FXMLLoader.load(getClass().getResource("EnterUserNameView.fxml"));
		Scene joinWindowScene = new Scene(joinWindowParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(joinWindowScene);
		window.show();
	}

}
