package Main.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SignUpController implements Initializable{
	 
	@FXML
	 private Stage stage;
	 private Parent root; 
	
	 @FXML ImageView signuplogo;
	 @FXML Image heartlogo;
	
	
	@Override
	  public void initialize(URL location, ResourceBundle resources) {

		  heartlogo = new Image(getClass().getResourceAsStream("heartlogo.png"));
		  signuplogo.setImage(heartlogo);

	  }
	
	public void backtologinbutton(ActionEvent event) throws IOException 
	{
		root = FXMLLoader.load(getClass().getResource("Main Login.fxml"));
		Scene newscene  = new Scene(root);
		  
		Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
		window.setScene(newscene);
		window.show();
	}

}
