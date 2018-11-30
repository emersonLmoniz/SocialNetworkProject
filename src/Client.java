import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* 
 * GUI Portion of the Client
 * This GUI interacts with the client 
 * Written by: Emerson Moniz
 * Date: 11/30/2018
 */
public class Client extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
//		Load the Client GUI  
		Parent mainWindow = FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
		primaryStage.setTitle("NP Chatroom");
		primaryStage.setScene(new Scene(mainWindow,400,150));
		primaryStage.show();
	}
}
