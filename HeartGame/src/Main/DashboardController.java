package Main;

import java.io.IOException;
import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DashboardController {
	
	 private Parent root;
	 @FXML private Text question_no;
	 @FXML private Text total_questions;
	 @FXML private Text level_text;
	 @FXML private Text countdowntimer;
	 @FXML private Button settingsbutton;
	 @FXML private Button statisticsbutton;
	 @FXML private Button gamestartbutton;
	 @FXML private TextField usernametextfield=null;
	 
	 private static final Integer STARTTIME = 15;
	 private Timeline timeline;
	 private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

	 
	public void settingsbuttonclicked(ActionEvent event) throws IOException 
	{
		  root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
		  Scene newscene  = new Scene(root);
		  
		  Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
		  window.setScene(newscene);
		  window.show();
	}
	
	
	/*the code for the "statisticsbuttonclicked" method is adapted from following websites and videos
	 * 1."https://www.youtube.com/watch?v=za098UQPEGA"
	 * 2."https://stackoverflow.com/questions/31351103/javafx-textinputdialog-disable-ok-button"
	 * */
	public void sessionsavebuttonclicked(ActionEvent event) throws IOException 
	{
		TextInputDialog td = new TextInputDialog("");
		td.setTitle("Session Save Warning");
		td.setHeaderText("Please enter a session name to save the current session"); 
		td.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);

		Optional<String> result = td.showAndWait();
		TextField input = td.getEditor();

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

	
	public void gamestartbuttonclicked(ActionEvent e) 
	{
		e.consume();
		this.gamestartwarning();		
	}
	
	public void resumeplaybuttonaction(ActionEvent e) 
	{
				
	}
	
	
	public void gamestartwarning() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Are you sure you want to start the game?");
		alert.setHeaderText("Game Start Warning");
		alert.setContentText("Game Level 1 consists of 6 Questions");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		    	this.initDashboard();
				this.countdowntimer();
		    }
		});
	}
	
	
	public void initDashboard() 
	{
		question_no.setText("1");
		total_questions.setText("6");
		level_text.setText("1");
	
	}
	
	public void statisticsloginwarning(ActionEvent event) 
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Guest Mode Warning");
		alert.setHeaderText("Limited feature");
		alert.setContentText("Please login to access the all features"+"\n"+"Click okay to show preview");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		   	    try {
					root = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Scene newscene  = new Scene(root);
				  
				Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
				window.setScene(newscene);
				window.show();
		    	
		    	
		    }
		});
		
	}
	
	public void settingsloginwarning(ActionEvent event) 
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Guest Mode Warning");
		alert.setHeaderText("Limited feature");
		alert.setContentText("Please login to access the all features"+"\n"+"Click okay to show preview");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		   	    try {
					root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene newscene  = new Scene(root);
				Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
				window.setScene(newscene);
				window.show();
		    	
		    	
		    }
		});
		
	}
	
	public void gamerestartwarning() 
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Game Restart Warning");
		alert.setHeaderText("Are you sure want to restart the level?");
		alert.setContentText("All your current progress will be lost");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		    	
		    	
		    }
		});
		
	}
	
	public void gamerepausewarning() 
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Game Pause Warning");
		alert.setHeaderText("Game Paused");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		    	
		    	
		    }
		});
		
	}
	
	public void countdowntimer() 
	{
		countdowntimer.textProperty().bind(timeSeconds.asString());
		gamestartbutton.setVisible(false);
		gamestartbutton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds.set(STARTTIME);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
                timeline.playFromStart();
            }
        });
	}
	
	public void gameexitwarning() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Login Warning");
		alert.setHeaderText("Are you sure you want to logout from the game?"+"/n All your current"
				+ "information will be lost");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		    	question_no.setText("1");
				total_questions.setText("6");
				level_text.setText("1");
		    }
		});
	}

}
