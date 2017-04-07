//GameBoard.java
//Creates the "graveyard" board
//Implements the objectives (souls) and obstacles (headstones)
//Prints the board
//Checks for a win
//Author(s): Elizabeth Wang, Sarah Chong

import java.util.*;

public class GameBoard
{
	public char[][] field;
	private int len; //size of the grid

	GameBoard() //Generates a board
	{
		len = 6;

		//Random Number Generator
		//int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
		//Put a method that checks "validSpace()", where True is when the space is a '-'

		field = new char[len][len]; //6x6 character array

		for(int i = 0; i < len; i++)
		{
			for(int j = 0; j < len; j++)
			{
				//Creates a border of '+' around the array.
				if(i == 0 || i == len - 1 || j == 0 || j == len -1 )
				{
					field[i][j] = '+';
				}
				else
				{
					field[i][j] = '-';
				}
			}
		}
	}
	GameBoard(int length)
	{
		len = length;

		//Random Number Generator
		//int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
		//Put a method that checks "validSpace()", where True is when the space is a '-'
		field = new char[len][len];

		for(int i = 0; i < len; i++)
		{
			for(int j = 0; j < len; j++)
			{
				//Creates a border of '+' around the array.
				if(i == 0 || i == len - 1 || j == 0 || j == len -1 )
				{
					field[i][j] = '+';
				}
				else
				{
					field[i][j] = '-';
				}
			}
		}

	}
	public String getBoard()
	{
		String temp = "";

		for(int i = 0; i < len; i++)
		{
			for(int j = 0; j < len; j++)
			{
				temp += field[i][j];
			}
			temp += '\n';
		}

		return temp;
	}
	public void addPiece(int x, int y)
	{
		//Replaces '-' with another thing.
		field[x][y] = 'O';
	}
	public void remPiece(int x, int y)
	{
		//Replaces spaces with '-'
		field[x][y] = '-';
	}
	public boolean checkWin()
	{
		//Checks if all points have been collected from board
		return true;
	}

	//This is a checker method
	public static void main(String[] args)
	{

		//addPiece(1,1);
		GameBoard board = new GameBoard();
		board.addPiece(1,1);
		System.out.println(board.getBoard());
		board.remPiece(1,1);
		System.out.println(board.getBoard());
	}
}//GameBoard
