//PlayerPiece.java
//Creates a player piece that will track its symbol and its location on the board
//Author(s): Elizabeth Wang, Sarah Chong

import java.util.*;

public class PlayerPiece2
{
	private int coord_x;
	private int coord_y;
	private char num;
	private int score;
	
	//Generates a piece, stores initial location
	PlayerPiece(int x, int y, char number)
	{
		score = 0;
		coord_x = x;
		coord_y = y;
		num = number;
	}	
	//Returns the assigned player number
	public char getPiece()
	{
		return num;
	}
	//Broadcasts its current location
	//in an array of length 2
	public int[] getSpace()
	{
		int[] temp = new int[2];
		temp[0] = coord_x;
		temp[1] = coord_y;
		return temp;
	}
	//Updates the location
	public void newSpace(int x, int y)
	{
		coord_x = x;
		coord_y = y;
	}
}//PlayerPiece