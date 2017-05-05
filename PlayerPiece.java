//PlayerPiece.java
//Creates a player piece that will track its symbol and its location on the board
//Author(s): Elizabeth Wang, Sarah Chong

import java.util.*;

public class PlayerPiece
{
	private int coord_x;
	private int coord_y;
	private char shape;
	
	//Generates a piece, stores initial location
	PlayerPiece(int x, int y, char symbol)
	{
		coord_x = x;
		coord_y = y;
		shape = symbol;
	}
	
	//Returns the assigned symbol
	public char getPiece()
	{
		return shape;
	}
	//Broadcasts its current location
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