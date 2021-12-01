package Main.Levels;

public class Level3Initializer implements Levelinitializer{
	
	private int level;
	
	private int levelquestions;

	private boolean status;
	
	private int score;
	
	private int questions;

	
	private int correctanswers;
	
	private int incorrectanswers;
	
	private int timer;
	
	public Level3Initializer()
	{
		level=3;
		
		questions=1;
		
		status = false;
		
		levelquestions=12;
		
		timer=8;

	}
	
	public void reset() 
	{
        questions=1;
		
		status = false;
	}
	
	public void setstatus() 
	{
		status = true;
	}
	
	public boolean getstatus() 
	{
		return status;
	}


	public int getScore() {
		return score;
	}

	public void setScore() {
		score++;
	}

	public int getQuestions() {
		return questions;
	}

	public void setQuestions() {
		questions++;
	}

	public int getCorrectanswers() {
		return correctanswers;
	}

	public void setCorrectanswers() {
		this.correctanswers++;
	}

	public int getIncorrectanswers() {
		return incorrectanswers;
	}

	public void setIncorrectanswers(int incorrectanswers) {
		this.incorrectanswers = incorrectanswers;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public int getLevel() {
		return level;
	}

	public int getLevelquestions() {
		return levelquestions;
	}
	
}
	
	
	
	
	