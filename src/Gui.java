//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//
// Will delete later still extracting

//public class Gui extends Application{
//
//	Button btnChoiceJoin, btnChoiceCreate,btnNext,btnBack;
//	Scene scnJoin,scnCreate;
////	ComboBox you need to declare the type in <>
//	ComboBox<String> chatRoomComboBox;
//	Label lblWelcome;
//	public static void main(String[] args) {
//    	launch(args);
//    }
//	
//    public void start(Stage primaryStage) throws Exception {
//    	// Label just creates text that the user can't modify
//    	lblWelcome = new Label("Welcome to Chatroom what would you like to do?");
//    	// Create a Button and set the text directly   	
//        btnChoiceJoin = new Button("Join");
//        //    	btnChoiceJoin.setText("Join");
//    	btnChoiceCreate = new Button("Create");
//    	// Alternative version would be just create the button then set text 
//    	//        btnChoiceCreate.setText("Create");
//    	chatRoomComboBox = new ComboBox<>();
//    	chatRoomComboBox.getItems().addAll(
//    			"Dummy1 ",
//    			"Dummy2",
//    			"3 baby");
//    	chatRoomComboBox.setPromptText("What is the chatroom join?");
//    	chatRoomComboBox.setOnAction(e -> System.out.println("User selected: " + chatRoomComboBox.getValue()));
////    	scnJoin = new Scene()
//    	
//        btnChoiceJoin.setOnAction(e-> {
////        	TODO: CHANGE THE SCENE TO JOIN PAGE
//        	System.out.println("User pressed Join");
////        	primaryStage.setScene(scnJoin);
//        });
//        btnChoiceCreate.setOnAction(e->{ 
////        	TODO: CHANGE THE SCENE TO CHOICE PAGE
//        	System.out.println("User pressed Create");
////        	primaryStage.setScene(scnCreate);
//        });
////        btnNext.setOnAction(e-> {
////        	TODO: CHANGE THE SCENE TO LET USER ENTER USER NAME
////        	primaryStage.setScene();
////        });
//        
//        // Create Hbox to hold the buttons
//        HBox hbButtons = new HBox(15,btnChoiceJoin,btnChoiceCreate);
//        hbButtons.setAlignment(Pos.CENTER);
//        
//        GridPane root = new GridPane();
//        root.setPadding(new Insets(10,10,10,10));
//        root.setAlignment(Pos.CENTER);
//        root.add(lblWelcome, 0, 0);
//        root.add(hbButtons, 0, 1);
////        root.getChildren().addAll(lblWelcome,hbButtons);
//
//        Scene scene = new Scene(root, 300, 250);
//
//        primaryStage.setTitle("Net.Programming Chatroom");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//}
