package Main.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;



public class GUIController implements Initializable{
	
	
	 @FXML
	 private Stage stage;
	 private Parent root;
	 
	 
	 @FXML ImageView mainlogo;
	 @FXML Image heartlogo;
	 
	 
	 @Override
	  public void initialize(URL location, ResourceBundle resources) {

		  heartlogo = new Image(getClass().getResourceAsStream("heartlogo.png"));
		  mainlogo.setImage(heartlogo);

	  }
	 
	 
	 
	 public void loginmodestartedwarning() 
		{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Guest Login Mode");
				alert.setContentText("Enjoy the game with limited features");
				alert.show();
		} 

	 
		public void forgetpasswordclicked(ActionEvent event) throws IOException 
		{
			
			
			  root = FXMLLoader.load(getClass().getResource("PasswordReset.fxml"));
			  Scene newscene  = new Scene(root);
			  
			  Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
			  window.close();
			  window.setScene(newscene);
			  window.show();
		}
		
		
		public void signupbuttonclicked(ActionEvent event) throws IOException 
		{
			
			
			  root = FXMLLoader.load(getClass().getResource("Sign Up.fxml"));
			  Scene newscene  = new Scene(root);
			  
			  Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
			  window.close();
			  window.setScene(newscene);
			  window.show();
		}
		
		
		public void guestloginbuttonclicked(ActionEvent event) throws IOException 
		{ 
			  root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			  Scene newscene  = new Scene(root);
			  
			  
			  Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
			  window.close();
			  window.setScene(newscene);
			  window.show();
			  this.loginmodestartedwarning();
			
		}
	
	
	
	  
	
		
}
