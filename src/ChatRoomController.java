import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/* 
 * Controller of the Chatroom 
 * Decrypts, displays the message in chatroom and the user list
 * Written by: Emerson Moniz
 * Date:11/30/2018
 */
public class ChatRoomController extends Algorithm{
	private MainWindowController mainWindow;
	private Button btnSend, btnDecrypt;
	private TextArea taMessage, taDecryptMessage,taDecryptedMessage;
	Algorithm a;
	ClientHandler c = new ClientHandler(mainWindow.getUserName());
	public void sendEncryptedMessage(ActionEvent event) {
		String regularMessage,encryptedMessage;
		regularMessage= taMessage.getText();
		encryptedMessage=Algorithm.encrypt(regularMessage, "James");
		c.setMessage(encryptedMessage);
	}
	
	public void decryptMessage(ActionEvent event) {
		String regularMessage,decryptedMessage;
		regularMessage= taDecryptMessage.getText();
		decryptedMessage=Algorithm.decrypt(regularMessage, "James");
		taDecryptedMessage.setText(decryptedMessage);
	}
	
	public void viewListOfUsers(ActionEvent event) {
		
	}

	public void init(MainWindowController mainWindowController) {
		// TODO Auto-generated method stub
		mainWindow = mainWindowController;
	}
	
}
