package Main.GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

	
	  
	

	   @Override
	    public void start(Stage primaryStage) throws IOException {
	            Parent root = FXMLLoader.load(getClass().getResource("Main Login.fxml"));
	            primaryStage.setResizable(false);
	            
	            primaryStage.setTitle("Heart Game 3.0");
	            primaryStage.setScene(new Scene(root));
	            primaryStage.show();

	            

	    }
	   
	    public static void main(String[] args) {
	        launch(args);
	    }
}
