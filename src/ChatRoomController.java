import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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
	    private TextArea tfEncryptedMessage;

	    @FXML
	    private TextArea tfDecryptedMessage;

	    @FXML
	    private Button btnDecrypt;

	    @FXML
	    private TextArea taChatRoom;

	    @FXML
	    private TextArea taMessage;

	    @FXML
	    private TextArea taUserlist;

	    @FXML
	    private Button btnSend;
	    
        String msgin = "";

	    
	    @FXML
	    void DecryptMessage(ActionEvent event) {

	    }

	    @FXML
	    void EncryptMessage(ActionEvent event) {

	    }
	    public void clickStartChatroom(ActionEvent event) throws IOException {
	    }
	    @FXML // This method is called by the FXMLLoader when initialization is complete
	    void initialize() {
	        assert tfEncryptedMessage != null : "fx:id=\"tfEncryptedMessage\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
	        assert tfDecryptedMessage != null : "fx:id=\"tfDecryptedMessage\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
	        assert btnDecrypt != null : "fx:id=\"btnDecrypt\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
	        assert taChatRoom != null : "fx:id=\"taChatRoom\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
	        assert taMessage != null : "fx:id=\"taMessage\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
	        assert taUserlist != null : "fx:id=\"taUserlist\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
	        assert btnSend != null : "fx:id=\"btnSend\" was not injected: check your FXML file 'ChatRoomView.fxml'.";
			try {
				Socket cs;

				cs = new Socket("localhost", 8800);
				DataInputStream dins = new DataInputStream(cs.getInputStream());
		    	Thread readMessage = new Thread(new Runnable() {
					public void run() {
						while (true) {
							
							try {
								msgin = dins.readUTF();
								taChatRoom.appendText(msgin);
//								System.out.println(msgin);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				});
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // server
	        
//	    	readMessage.start();

	    }
}
