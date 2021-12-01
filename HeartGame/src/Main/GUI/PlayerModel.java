package Main.GUI;



public class PlayerModel {
	
	private int playerId;
    private String playerName;
    private int playerScore;

    
    public PlayerModel() 
    {
    	this.playerId = 0;
    	this.playerName = "Guest";
    	this.playerScore = 0;

    }


	public int getPlayerId() {
		return playerId;
	}


	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public int getPlayerScore() {
		return playerScore;
	}


	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
    
    


	
    
    

}
