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
				if(i == 0 || i == len - 1 || j == 0 || j == len - 1 )
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
		//Replaces '-' with another thing if correct numbers are entered
		outerloop:
		for(int i = 0; i < len; i++)
		{
			for(int j = 0; j < len; j++)
			{
				if(x <= 0 || x >= len - 1 || y <= 0 || y >= len - 1)
				{
					System.out.println("INCORRECT MOVE.");
					break outerloop;
				}

				else if (field[x][y] == 'O')
				{
					System.out.println("THERE IS ALREADY A PIECE AT THAT SPACE.");
					break outerloop;
				}

				else
				{
					field[x][y] = 'O';
					break outerloop;
				}
			}
		}
	}
	public void remPiece(int x, int y)
	{
		//Replaces spaces with '-' if correct numbers are entered
		outerloop2:
		for(int i = 0; i < len; i++)
		{
			for(int j = 0; j < len; j++)
			{
				if(x <= 0 || x >= len - 1 || y <= 0 || y >= len - 1)
				{
					System.out.println("INCORRECT MOVE.");
					break outerloop2;
				}

				else if (field[x][y] == '-')
				{
					System.out.println("THAT SPACE IS ALREADY EMPTY.");
					break outerloop2;
				}

				else
				{
					field[x][y] = 'O';
					break outerloop2;
				}
			}
		}
	}
	public boolean checkWin()
	{
		//Checks if all points have been collected from board
		return true;
	}

	//This is a checker method
	public static void main(String[] args)
	{
		//Test if addPiece works
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER A PIECE TO ADD TO THE GAMEBOARD ON THE X AXIS (CHOOSE # FROM 1-4)");
		int x = input.nextInt();
		System.out.println("ENTER A PIECE TO ADD TO THE GAMEBOARD ON THE Y AXIS (CHOOSE # FROM 1-4)");
		int y = input.nextInt();

		GameBoard board = new GameBoard();
		board.addPiece(x,y);
		System.out.println(board.getBoard());
		board.remPiece(1,1);
		System.out.println(board.getBoard());
	}
}//GameBoard
