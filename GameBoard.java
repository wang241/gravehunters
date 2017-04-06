//GameBoard.java
//Creates the "graveyard" board
//Implements the objectives (souls) and obstacles (headstones)
//Prints the board
//Checks for a win
//Author(s): Elizabeth Wang, Sarah Chong

import java.util.*;

pulic class GameBoard
{
	public char[] field;
	
	GameBoard()
	{
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
		//This generates a random number
		//Need to initialize a 5x5 array of '-'
	}
	public String getBoard()
	{
		//Prints board into a string
	}
	public void addPiece(int x, int y)
	{
		//Replaces '-' with another thing.
	}
	public void remPiece(int x, int y)
	{
		//Replaces spaces with '-'
	}
	public boolean checkWin()
	{
		//Checks if all points have been collected from board
	}
}