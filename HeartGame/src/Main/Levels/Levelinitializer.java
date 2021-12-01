package Main.Levels;

public interface Levelinitializer {
	

	public boolean getstatus();
	
	public void setstatus();
	
	public int getScore();

	public void setScore();

	public int getQuestions();

	public void setQuestions();

	public int getCorrectanswers();

	public void setCorrectanswers();

	public int getIncorrectanswers();

	public void setIncorrectanswers(int incorrectanswers);

	public int getTimer();

	public void setTimer(int timer);

	public int getLevel();

	public int getLevelquestions();
	
	public void reset();


}
