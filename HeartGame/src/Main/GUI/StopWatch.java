package Main.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.util.Timer;
import java.util.TimerTask;


public class StopWatch{
	
	
	@FXML TextField timertext;
	
	TimerTask task;
	
	int elapsedtime;
	int seconds;
    int minutes;
	int hours;
	
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);
	

	 
	     
	public StopWatch() 
	{
		

	}
		
	
	public void start() 
	{
         task.run();
	}
	
	public void stop() throws InterruptedException 
	{
		  task.wait();

	}
	
	
	
}