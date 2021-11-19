package Main.GUI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Main.Game.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class DashboardController implements Initializable{
	
	 
	@FXML
	Parent root;

	  
	 @FXML Text question_no;
	 @FXML Text total_questions;
	 @FXML Text level_text;
	 @FXML Text countdowntimer;
	 @FXML Button settingsbutton;
	 @FXML Button statisticsbutton;
	 @FXML Button gamestartbutton;
	 @FXML TextField usernametextfield=null;
	 @FXML ImageView questionarea;
	 @FXML TextField currentscore; 
	 @FXML ImageView logo;
	 @FXML Image heartlogo;
	 @FXML Image image;
	 
	 @FXML Button answer1;
	 @FXML Button answer2;
	 @FXML Button answer3;
	 @FXML Button answer4;
	 @FXML Button answer5;
	 @FXML Button answer6;
	 @FXML Button answer7;
	 @FXML Button answer8;
	 @FXML Button answer9;


	 
	 private static final Integer STARTTIME = 15;
	 private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
	 private Timeline timeline;
	 
	 GameEngine myGame;
	 URL currentgame=null;
	 
	 
	 /*the code for the "initialize" method is adapted from following websites and videos
		 * 1."https://stackoverflow.com/questions/37363846/imageview-setimage-nullpointer-exception"
		 * */
	  @Override
	  public void initialize(URL location, ResourceBundle resources) {

		  heartlogo = new Image(getClass().getResourceAsStream("heartlogo.png"));
		  logo.setImage(heartlogo);

	  }
	
	

	/*the code for the "buttonHandler" method is adapted from following websites and videos
	 * 1."https://stackoverflow.com/questions/40757911/javafx-adding-actionlistener-to-button"
	 * */
	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent e) {
	    	String text = answer1.getText();
			int solution = Integer.parseInt(text);
			boolean correct = myGame.checkSolution(currentgame, solution);
			int score = myGame.getScore();

			if (correct) {
				System.out.println("YEAH!");
				currentgame = myGame.nextGame();
		        currentscore.setText(Integer.toString(score));
			} else { 
				System.out.println("Not Correct"); 
				//infoArea.setText("Oops. Try again!  Score: "+score);
			}
	    }
	};
	
	
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
		    	try {
					this.initDashboard();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.countdowntimer();
		    }
		});
	}
	
	
	public void initDashboard() throws URISyntaxException 
	{
		question_no.setText("1");
		total_questions.setText("6");
		level_text.setText("1");
		myGame = new GameEngine();
		currentgame = myGame.nextGame();
		image = new Image(currentgame.toString());
        questionarea = new ImageView(image);
		answer1.setOnAction(buttonHandler);
		answer2.setOnAction(buttonHandler);
		answer3.setOnAction(buttonHandler);
		answer4.setOnAction(buttonHandler);
		answer5.setOnAction(buttonHandler);
		answer6.setOnAction(buttonHandler);
		answer7.setOnAction(buttonHandler);
		answer8.setOnAction(buttonHandler);
		answer9.setOnAction(buttonHandler);

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
				window.close();
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
				window.close();
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
