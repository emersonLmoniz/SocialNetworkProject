import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/* 
 * Controller of the Chatroom 
 * Decrypts, displays the message in chatroom and the user list
 * Written by: Emerson Moniz
 * Date:11/30/2018
 */
public class ChatRoomController {
	    @FXML
	    private TextArea taChatRoom;

	    @FXML
	    private TextArea taMessage;

	    @FXML
	    private TextArea taUserlist;

	    @FXML
	    private Button btnSend;
	
	    //When you press enter butoon:
	    public void clickStartChatroom(ActionEvent event) throws IOException {
	    	// send the user message to sever...
	    	String usermessage = taMessage.getText();
	    	Client.getSendMessage(usermessage);
	    	// I don't understand how you get the message from clienthandler...
	    	taChatRoom.setText("?");
	    	
	    	
	    }
	    @FXML // This method is called by the FXMLLoader when initialization is complete
	    void initialize() {
	        assert taChatRoom != null : "fx:id=\"taChatRoom\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
	        assert taMessage != null : "fx:id=\"taMessage\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
	        assert taUserlist != null : "fx:id=\"taUserlist\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
	        assert btnSend != null : "fx:id=\"btnSend\" was not injected: check your FXML file 'ChatRoomView.fxml'.";

	    }
	    // and we need a method to keep update the message
	    //extra : we can cancel the userlist.... but if we have time ,we can also update the userlist...
	    
}
