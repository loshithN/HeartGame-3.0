package Main.GUI;

import java.util.*;


public class TimerTask {
	
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask() {
			
			int counter = 15;
			public void run() {
				if(counter>0) {
					System.out.println(counter+" seconds");
					counter--;
				}
				else {
					System.out.println("HAPPY NEW YEAR!");
					timer.cancel();
				}
			}		
		};
		
		

}
