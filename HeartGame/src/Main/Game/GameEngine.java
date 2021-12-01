package Main.Game;

import java.net.MalformedURLException;
import java.net.URL;
import Main.Server.*;

/**
 * Main class where the games are coming from. 
 * Basic functionality
 */
public class GameEngine {
	
	
	String thePlayer = null;
	String time;

	/**
	 * Each player has their own game engine.
	 * 
	 * @param player
	 */
	public GameEngine() {
		//thePlayer = player;
	}

	int counter = 0;
	int level = 0;
	int leaderboard=0;
	int questionsanswered=0;
	int score = 0; 
	GameServer theGames = new GameServer(); 
	Game current = null; 
/*
 * Retrieves a game. This basic version only has two games that alternate.
 */
	public URL nextGame() {		
			try {
				current = theGames.getRandomGame();
				return current.getLocation(); 
			} catch (MalformedURLException e) {
				System.out.println("Something went wrong when trying to retrieve game "+counter+"!"); 
				e.printStackTrace();
				return null; 
			} 
		
	}
	
	
	

	public int getCounter() {
		return counter;
	}




	public void setCounter(int counter) {
		this.counter = counter;
	}




	public void setScore(int score) {
		this.score = score;
	}




	/**
	 * Checks if the parameter i is a solution to the game URL. If so, score is increased by one. 
	 * @param game
	 * @param i
	 * @return
	 */
	public boolean checkSolution(URL game, int i) {
		if (i == current.getSolution()) {
			score++; 
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Retrieves the score. 
	 * 
	 * @param player
	 * @return
	 */
	public int getScore() {
		return score;
	}

}
