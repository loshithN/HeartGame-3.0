package Main.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SignUpController implements Initializable{
	 
	 @FXML
	 private Stage stage;
	 private Parent root; 
	 private Stage window;
	 
	 @FXML
	 TextField namefield;
	 TextField aliasfield;
	 TextField usernamefield;
	 TextField passwordfield;
	 TextField confirmpasswordfield;

	
	 @FXML ImageView signuplogo;
	 @FXML Image heartlogo;
	 
	 @FXML Button reguserbutton;
	
	
	@Override
	  public void initialize(URL location, ResourceBundle resources) {

		  heartlogo = new Image(getClass().getResourceAsStream("heartlogo.png"));
		  signuplogo.setImage(heartlogo);
		  reguserbutton.setOnAction(signupbuttonHandler);

	  }
	
	public void backtologinbutton(ActionEvent event) throws IOException 
	{
		root = FXMLLoader.load(getClass().getResource("Main Login.fxml"));
		Scene newscene  = new Scene(root);
		  
		window =   (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
		window.setScene(newscene);
		window.show();
	}
	
	private void showAlert(Alert.AlertType alertType,String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
	
	EventHandler<ActionEvent> signupbuttonHandler = new EventHandler<ActionEvent>() {
	    
		@Override
	    public void handle(ActionEvent event) {
	        if(namefield.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, 
	            "Form Error!", "Please enter your name");
	            return;
	        }
	        if(aliasfield.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, 
	            "Form Error!", "Please enter a alias");
	            return;
	        }
	        if(passwordfield.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, 
	            "Form Error!", "Please enter a password");
	            return;
	        }
	        if(confirmpasswordfield.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, 
	            "Form Error!", "Please reenter the password");
	            return;
	        }
            
	        showAlert(Alert.AlertType.CONFIRMATION, 
	        "Registration Successful!", "Welcome " + namefield.getText());
	    }
	};
	
	
	
	

}
