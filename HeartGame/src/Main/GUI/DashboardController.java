package Main.GUI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import Main.Game.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import Main.Levels.*;

public class DashboardController implements Initializable{
	
	 
	
	 @FXML
	 Parent root;
	
	 //private static final Integer STARTTIME = 15;
	 //private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
	 private int solution;
	 private boolean correct;
	 private int score;
	 
	 private double levelprogress = 0.0;
	 private double gameprogress = 0.0;
	 
	 Levelinitializer newinit;

	 
	 
	    TimerTask task;
		
		int elapsedtime;
		int seconds;
	    int minutes;
		int hours;
		
		String seconds_string = String.format("%02d", seconds);
		String minutes_string = String.format("%02d", minutes);
		String hours_string = String.format("%02d", hours);

	 
	 GameEngine myGame;
	 URL currentgame=null;
	 
	 
	 
	 Level1Initializer level1init = new Level1Initializer();	 
	 Level2Initializer level2init = new Level2Initializer();	 
	 Level3Initializer level3init = new Level3Initializer();	 

     	  
	 @FXML Text question_no;
	 @FXML Text total_questions;
	 @FXML Text level_text;
	 @FXML Text countdowntimer;
	 
	 @FXML Button settingsbutton;
	 @FXML Button statisticsbutton;
	 @FXML Button gamestartbutton;
	 
	 @FXML TextField usernametextfield=null;
	 @FXML TextField timertext;
	 
	 @FXML Text answertext;

	 
	 @FXML ImageView logo;
	 @FXML ImageView questionarea;

	 
	 @FXML Image heartlogo;
	 @FXML Image questionimage;
	 
	 @FXML TableView<PlayerModel> leaderboardtable;
	 
	 PlayerModel newplayer = new PlayerModel();

	 
	 @FXML Button answer1;
	 @FXML Button answer2;
	 @FXML Button answer3;
	 @FXML Button answer4;
	 @FXML Button answer5;
	 @FXML Button answer6;
	 @FXML Button answer7;
	 @FXML Button answer8;
	 @FXML Button answer9;
	 
	 @FXML TextField gamescore;
	 
	 
	 @FXML Button resumeplaybutton;
	 
	 @FXML ProgressBar levelprogressbar;
	 @FXML ProgressBar gameprogressbar;
	 
	 Timer timer;
	 
	 Timer count;
	 
	 Timer dialogcount;

	 String text;
	 
	 String value;
	 
	 int countdowninterval;
	 
	 boolean level1status=false;
	 boolean level2status=false;
	 boolean level3status=false;

	 
	 ButtonType levelresult;
	 
	 
	 /*the code for the "setTimer" method is adapted from following websites and videos
		 * 1."https://stackoverflow.com/questions/47655695/javafx-countdown-timer-in-label-settext"
		 * */
	 public void setcountdownTimer() {
		    count.scheduleAtFixedRate(new TimerTask() {
		        public void run() {
	            	countdowntimer.setStyle("-fx-text-fill: red;");
		            if(countdowninterval > 0)
		            {
		                Platform.runLater(() -> countdowntimer.setText(Integer.toString(countdowninterval)));
		                countdowninterval--;
		            }
		            else 
		            {
		     		    //correct = myGame.checkSolution(currentgame, 0);
		            	gamescore.setText(Integer.toString(score));
		       	        newinit.setQuestions();
		       	        increasegamelevelProgress();
		       		    levelinitializer();
		       		    countdowninterval = newinit.getTimer();
		       		    count.cancel();
		       		    count = new Timer();
		       		    setcountdownTimer();
		            	/*Platform.runLater(() ->{
							solutionchecker();
						});*/
		            }
		        }
		    }, 1000,1000);
		}
	 
  
	 public void levelinitializer() 
	 {
	   
	   if(level1init.getstatus()==false) 
	   {
		   levelcontroller(level1init);
		   newinit =levelgameoperator(level1init);
	   }
	   else 
	   {
		   if(level1status==false) 
		   {
			   value =checklevel(level1init);
			   level1status=true;
		   }
		   level1operator();
	   }   

	  }
	   
	   public void level2initializer()
	   {
		   if(level2init.getstatus()==false)
		   {
			   levelcontroller(level2init);
			   newinit =levelgameoperator(level2init);
		   } 
		   else
		   {
			   if(level2status==false) 
			   {
				   value =checklevel(level2init);
				   level2status=true;
			   }
			   level2operator(); 
		}

	   }
	   
	   public void level3initializer()
	   {
		   if(level3init.getstatus()==false)
		   {
			   levelcontroller(level3init);
			   newinit =levelgameoperator(level3init);
		   }
		   else if(level3init.getstatus()==true) 
		   {
			   if(level3status==false) 
			   {
				   value =checklevel(level3init);
				   level3status=true;
			   }
			   level3operator(); 
		   }

	   }
	   
	   
	   public void level1operator() 
	   {
		     if(value.compareTo("OK_DONE")==0) 
			 {
			  level2initializer();
			 }
		     else 
		     {
		    	 level1operator();
		     }
	   }
	   
	   
	   public void level2operator() 
	   {
		     if(value.compareTo("OK_DONE")==0) 
			 {
			  level3initializer();
			 }
		     else
		     {
		    	 level2operator();
		     }

	   }
	   
	   public void level3operator() 
	   {
		     if(value.compareTo("OK_DONE")==0) 
			 {
		    	 
			 }
		     else
		     {
		    	 level3operator();
		     }
	   }
	   
	   
	   
	   
	   
	   
	   	 
	 
	 public void levelcontroller(Levelinitializer controller) 
	 {
		 level_text.setText(Integer.toString(controller.getLevel()));
		 total_questions.setText(Integer.toString(controller.getLevelquestions()));
		 countdowntimer.setText(Integer.toString(controller.getTimer()));	

	 }
	 
	 
	 public String checklevel(Levelinitializer controller) {

 		levelcompletion.setTitle( "Level "+ controller.getLevel() +" Summary" );
 		levelcompletion.setHeaderText( "Level " +controller.getLevel()+ " Score: "+controller.getScore()
 		+"\nLeve "+controller.getLevel() +" Correct Answers: "+controller.getCorrectanswers() +
 		"\nLevel "+controller.getLevel() +" Incorrect Answers: "+controller.getIncorrectanswers()+"\n");
 		//levelcompletion.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
    	((Button) levelcompletion.getDialogPane().lookupButton(ButtonType.OK)).setText("Continue to next level");
		levelcompletion.showAndWait();
		levelresult = levelcompletion.getResult();
		count.cancel();
		count = new Timer();
		setcountdownTimer();
		return levelresult.getButtonData().toString();
		}
	 
	 
	 
	 
	 public Levelinitializer levelgameoperator(Levelinitializer controller) 
		{

			
			         
			 		currentgame = myGame.nextGame();

					
					questionimage = new Image(currentgame.toString());
			        questionarea.setImage(questionimage);
			        
			        
    				question_no.setText(Integer.toString(controller.getQuestions()));
    				

    				countdowninterval=controller.getTimer();  
    				
    			    setcountdownTimer();
    				 				
                    return controller;
			        

		}	
	 
	 
	 public void solutionchecker() 
	 {


		 if(newinit.getLevelquestions()==newinit.getQuestions()) 
	     {
	    	 newinit.setstatus();
			 resetgamelevelProgress();
			 increasegameProgress();

	     }

		if (correct)
		{
			   newinit.setScore();
			   newinit.setCorrectanswers();
			   score = score+level1init.getScore();
			   correctalert.showAndWait();

		} 
		
		else
		{ 
			   incorrectalert.showAndWait(); 


	    } 
			 
	     gamescore.setText(Integer.toString(score));
	     newinit.setQuestions();
	     increasegamelevelProgress();
		 levelinitializer();
		 countdowninterval = newinit.getTimer();
		 count.cancel();
		 count = new Timer();
		 setcountdownTimer();
		 
	 }
	 
	 
	
     public void increasegamelevelProgress() {
 		
 			levelprogress += 0.17;
 			levelprogressbar.setProgress(levelprogress);
 	}
     
     public void resetgamelevelProgress() {
  		
			levelprogress =0;
			levelprogressbar.setProgress(levelprogress);
	}
     
     
     public void increasegameProgress() {
  		
		    gameprogress += 0.33;
			gameprogressbar.setProgress(gameprogress);
	}
     
     
     /*public void updatetable() 
     {
         
    	 
         
    	 leaderboardtable.getItems().add(newplayer);


     }*/
     
     
     
     
     
     
	 /*the code for the "initialize" method is adapted from following websites and videos
		 * 1."https://stackoverflow.com/questions/37363846/imageview-setimage-nullpointer-exception"
		 * */
	  @Override
	  public void initialize(URL location, ResourceBundle resources) {

		  heartlogo = new Image(getClass().getResourceAsStream("heartlogo.png"));
		  logo.setImage(heartlogo);	
		  
		  questionimage = new Image(getClass().getResourceAsStream("preview.png"));
		  questionarea.setImage(questionimage);
		  
		  answer1.setVisible(false);
	      answer2.setVisible(false);
	      answer3.setVisible(false);
	      answer4.setVisible(false);
	      answer5.setVisible(false);
	      answer6.setVisible(false);
	      answer7.setVisible(false);
		  answer8.setVisible(false);
		  answer9.setVisible(false);
		  
		  answertext.setVisible(false);
		  
		  

		  /*Timeline idlestage = new Timeline( new KeyFrame( Duration.seconds(1 ), new EventHandler<ActionEvent>()
		    {

		        @Override
		        public void handle( ActionEvent event )
		        {
		        	incorrectalert.hide();
		        	correctalert.hide();
		        }
		    } ) );
		    idlestage.setCycleCount( 1 );
		    idlestage.play();*/
		  
		  //countdowntimer.textProperty().bind(timeSeconds.asString());
		  
		  
		  
	  }
	  
	  
	  
	  
	    Alert incorrectalert = new Alert( Alert.AlertType.INFORMATION );
		Alert correctalert = new Alert( Alert.AlertType.INFORMATION );
		Alert levelcompletion = new Alert( Alert.AlertType.INFORMATION );	    
		
		
		
	/*the code for the "buttonHandler" method is adapted from following websites and videos
	 * 1."https://stackoverflow.com/questions/40757911/javafx-adding-actionlistener-to-button"
	 * 2."https://stackoverflow.com/questions/32391905/auto-close-dialog-with-cancel-response-after-timeout"
	 * */
	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent e) {
	    	
						
			Button btn = (Button) e.getSource();
	    	
	    	Timeline idlestage = new Timeline( new KeyFrame( Duration.seconds(1 ), new EventHandler<ActionEvent>()
		    {

		        @Override
		        public void handle( ActionEvent event )
		        {
		            incorrectalert.close();
		            correctalert.close();

		        }
		    } ) );
		    idlestage.setCycleCount( 1 );
		    idlestage.play();
		    
		    
			
		   solution = Integer.parseInt(btn.getText());
		   correct = myGame.checkSolution(currentgame, solution);
           solutionchecker();

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
		Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
		
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
				window.setScene(newscene);
				window.show();
		    }
		    else 
		    {
		    	window.show();
		    }
		    });
		  
	}

	
	public void gamestartbuttonclicked(ActionEvent e) 
	{
		e.consume();
		this.gamestartwarning(e);
	}
	
	public void resumeplaybuttonaction(ActionEvent e) 
	{
				
	}
	
	
	public void gamestartwarning(ActionEvent event) 
	{
		Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Are you sure you want to start the game?");
		alert.setHeaderText("Game Start Warning");
		alert.setContentText("Game Level 1 consists of 6 Questions");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		    	try {
					this.initDashboard();
					window.show();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
	}
	
	
	public void initDashboard() throws URISyntaxException, InterruptedException 
	{
		
		timer = new Timer();
		count = new Timer();
		//dialogcount = new Timer();
		 
	    task = new TimerTask() {
				
				@Override
				public void run() {
						 elapsedtime=elapsedtime+1000;
				 		 hours = (elapsedtime/3600000);
				 		 minutes = (elapsedtime/60000) % 60;
				 		 seconds = (elapsedtime/1000) % 60;
				 		 seconds_string = String.format("%02d", seconds);
				 		 minutes_string = String.format("%02d", minutes);
				 		 hours_string = String.format("%02d", hours);
				 		 timertext.setAlignment(Pos.BASELINE_CENTER);
				 		 timertext.setStyle("-fx-text-fill: red;");
				 		 timertext.setText(hours_string+":"+minutes_string+":"+seconds_string);		 
				}		
			};
		timer.scheduleAtFixedRate(task, 0, 1000);
		
		
		answer1.setOnAction(buttonHandler);
		answer2.setOnAction(buttonHandler);
		answer3.setOnAction(buttonHandler);
		answer4.setOnAction(buttonHandler);
		answer5.setOnAction(buttonHandler);
		answer6.setOnAction(buttonHandler);
		answer7.setOnAction(buttonHandler);
		answer8.setOnAction(buttonHandler);
		answer9.setOnAction(buttonHandler);
		
		correctalert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
		correctalert.setTitle( "Game Score" );
		correctalert.setHeaderText( "Correct" );
		
		incorrectalert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
		incorrectalert.setTitle( "Game Score" );
		incorrectalert.setHeaderText( "Not Correct" );
		incorrectalert.setContentText( "Oops. Try again!" );
		
		

		
		
		/*Timer t = new java.util.Timer();
		 t.schedule( 
		         new java.util.TimerTask() {
		             @Override
		             public void run() {
		            	 incorrectalert.hide();
				         correctalert.hide();
		             }
		         }, 
		         1000 
		 );*/
		

		myGame = new GameEngine();
        
		gamestartbutton.setVisible(false);
		
		answer1.setVisible(true);
	    answer2.setVisible(true);
	    answer3.setVisible(true);
	    answer4.setVisible(true);
	    answer5.setVisible(true);
	    answer6.setVisible(true);
	    answer7.setVisible(true);
		answer8.setVisible(true);
		answer9.setVisible(true);
		
		answertext.setVisible(true);
		      
        
		
				
		//countdowntimer.textProperty().bind(timeSeconds.asString());

		        
		timerstart();
		
	    increasegamelevelProgress();
		levelinitializer();

 		
 	

	}
	
	
	public void timerreset() 
	{
		  timer.cancel();
		  count.cancel();
		  task.cancel();
		  
		  resetgamelevelProgress();
		  
		  elapsedtime=0;
		  seconds =0;
		  minutes=0;
		  hours=0;
		  seconds_string = String.format("%02d", seconds);
		  minutes_string = String.format("%02d", minutes);
		  hours_string = String.format("%02d", hours);   
		  
		  timertext.setText(hours_string+":"+minutes_string+":"+seconds_string);

	}
	
	
	public void timerstart() throws InterruptedException 
	{
		  task.run();

	}
	
	
	
	EventHandler<ActionEvent> buttonHandler2 = new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent e) {
			
	    	
	    	Timeline checkprogresstimer = new Timeline( new KeyFrame( Duration.seconds(1 ), new EventHandler<ActionEvent>()
		    {

		        @Override
		        public void handle( ActionEvent event )
		        {
		        	/*if(level1init.checklevel1completion()==true) 
		        	{
			            checklevel();
		        	}*/
		        }
		    } ) );
		    checkprogresstimer.setCycleCount( 1 );
		    checkprogresstimer.play();
		    
	    }
		    
	    };
	
	
	
	
	public void statisticsloginwarning(ActionEvent event) throws IOException 
	{
		Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();

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
				window.close();
				window.setScene(newscene);
				window.show();
		    	
		    	
		    }
		    else 
		    {
		    	window.show();
		    }
		});
		
	}
	
	public void settingsloginwarning(ActionEvent event) 
	{
		Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
		
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
				window.close();
				window.setScene(newscene);
				window.show();
		    	
		    	
		    }
		    else 
		    {
		    	window.show();
		    }
		});
		
	}
	
	
	public void gamerestartwarning(ActionEvent event) 
	{
		Stage window =   (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
		
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Game Restart Warning");
		alert.setHeaderText("Are you sure want to restart the level?");
		alert.setContentText("All your current progress will be lost");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		    	
		    	try {
		    		timerreset();
		    		newinit.reset();
					initDashboard();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	window.show();
		    }
		    else 
		    {
		    	window.show();
		    }
		});
		
	}
	
	/*the code for the "gamerepausewarning" method is adapted from following websites and videos
	 * 1."https://stackoverflow.com/questions/36309385/how-to-change-the-text-of-yes-no-buttons-in-javafx-8-alert-dialogs"
	 * 2."https://stackoverflow.com/questions/52472046/alerts-in-javafx-do-not-close-when-x-button-is-pressed/52472182"
	 * */
	
	public void gamerepausewarning(ActionEvent event) throws InterruptedException 
	{
		Stage window1 =   (Stage)((Node)event.getSource()).getScene().getWindow();
		window1.close();
		
		
		Alert alert = new Alert(AlertType.WARNING);
    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Continue Game Play");
    	//Window window = alert.getDialogPane().getScene().getWindow();
        //window.setOnCloseRequest(e -> alert.hide());
    	alert.setTitle("Game Pause Warning");
		alert.setHeaderText("Game Paused");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		    	if(!gamestartbutton.isVisible()) 
		    	{
		    		window1.show();
		    	}
		    	
		    }
		    else 
		    {
		    	window1.show();
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
