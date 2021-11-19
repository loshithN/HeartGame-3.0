package Main.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class GameSettingsController implements Initializable{
	
	 @FXML
	 private Stage stage;
	 private Parent root;
	 
	 @FXML ImageView settingslogo;
	 @FXML Image heartlogo;
	
	
	@Override
	  public void initialize(URL location, ResourceBundle resources) {

		  heartlogo = new Image(getClass().getResourceAsStream("heartlogo.png"));
		  settingslogo.setImage(heartlogo);

	  }
	
	public void backtodashboard(ActionEvent event) throws IOException 
	{
		root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
		Scene newscene  = new Scene(root);
		  
		Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
		window.setScene(newscene);
		window.show();
	}
	
	public void logoutbuttonclicked(ActionEvent event) 
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Are you sure want to exit from the game?");
		alert.setHeaderText("Game Logout Warning");
		alert.setContentText("All your current informtion will be lost");
		alert.showAndWait().ifPresent(rs -> {
			event.consume();
		    if (rs == ButtonType.OK) {
		    	try {
					root = FXMLLoader.load(getClass().getResource("Main Login.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene newscene  = new Scene(root);
				  
				Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
				window.close();
				window.setScene(newscene);
				window.show();
		    }
		    });
		  
	}

}
