//GameBoard.java
//Creates the "graveyard" board
//Implements the objectives (souls) and obstacles (headstones)
//Prints the board
//Checks for a win
//Author(s): Elizabeth Wang, Sarah Chong

import java.util.*;

public class GameBoard
{
	public char[][] field; //Current board
	private int len; //size of the grid
	private int numO; //number of obstacles
	private int numP; //number of points
	private int score1; //score of player 1
	private int score2; //score of player 2

	GameBoard(int length) //Generates a board
	{
		len = length;
		numP = len / 3;
		numO = len / 2;

		field = new char[len][len]; //[length]x[length] character array

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

		addItemRan(numP, len, '*');
		addItemRan(numO, len, 'x');
	}

	//Place objects on the board at random
	public void addItemRan(int numObjects, int lengthBoard, char piece)
	{
		int i = 0; //counter
		while( i <= numObjects )
		{
			//Random Number Generator
			Random gen = new Random();
			int x = gen.nextInt(lengthBoard - 2) + 1;
			int y = gen.nextInt(lengthBoard - 2) + 1;

			//Checks x for valid number
			while(!checkSpace(x))
			{
				x = gen.nextInt(lengthBoard - 2) + 1;
			}
			while(!checkSpace(y))
			{
				y = gen.nextInt(lengthBoard - 2) + 1;
			}

			field[x][y] = piece;

			i++; //increment counter
		}
	}

	//Checks the content of a proposed move, returns a boolean if the move is valid, adds a point to the scoreboard.
	public boolean checkMove(int x, int y, int player)
	{
		if(field[x][y] == '-')
		{
			return true;
		}
		else if(field[x][y] == '*')
		{
			addScore(player);
			return true;
		}
		return false;
	}
	//Accesses the private variable of score, using player number to distinguish
	public void addScore(int player)
	{
		if(player == 1)
		{
			score1++;
		}
		else if(player == 2)
		{
			score2++;
		}
	}

	//Prints out the current score.
	public String getScore()
	{
		return "Player 1: " + score1 + " | " + "Player 2: " + score2;
	}

	//Checks if space exists on the board
	public boolean checkSpace(int num)
	{
		if( num < 0 || num > len )
		{
			return false;
		}
		return true;
	}

	//Prints current board.
	public String getBoard()
	{
		String temp = "";

		for(int i = 0; i < len; i++)
		{
			temp += i;
			for(int j = 0; j < len; j++)
			{
				temp += field[i][j];
				temp += " ";
			}
			temp += '\n';
		}

		return temp;
	}

	public void addPiece(char new_x, char new_y, PlayerPiece p)
	{
		int old_x = p.getSpace()[0];
		int old_y = p.getSpace()[1];

		if(field[new_y][new_x] == '-')
		{
			field[new_y][new_x] = p.getPiece();
			p.newSpace((int)new_x, (int)new_y);
			field[old_y][old_x] = '-';
		}
		else
		{
			System.out.println("INCORRECT MOVE.");
		}
	}

	public void addPiece(int x, int y, char symbol)
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

				else if (field[x][y] == symbol)
				{
					System.out.println("THERE IS ALREADY A PIECE AT THAT SPACE.");
					break outerloop;
				}

				else
				{
					field[x][y] = symbol;
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
					field[x][y] = '-';
					break outerloop2;
				}
			}
		}
	}

	//Checks if all points have been collected from board
	public boolean checkWin()
	{
		int total = score1 + score2;
		if(total == numP)
			return true;
		return false;
	}

	public boolean gameOver()
	{
		return false;
	}

	//This is a checker method
	public static void main(String[] args)
	{
		//Test if addPiece works
		int length = 10;
		GameBoard board = new GameBoard(length);
		PlayerPiece p1 = new PlayerPiece(1,1,'O');
		PlayerPiece p2 = new PlayerPiece(length-2, length-2,'$');

		//board.addPiece('1','1',p1);
		//board.addPiece('9','9',p2);

		System.out.println("HERE IS YOUR GAMEBOARD.");
		System.out.println(board.getBoard());

		Scanner input = new Scanner(System.in);

		int x = 0;
		int y = 0;

		while (!board.checkWin())
		{

			int x_remove = x;
			int y_remove = y;

			System.out.println("ENTER A PIECE TO ADD TO THE GAMEBOARD ON THE X AXIS (CHOOSE # FROM 1-" + (length-2) + ")");
			x = input.nextInt();
			System.out.println("ENTER A PIECE TO ADD TO THE GAMEBOARD ON THE Y AXIS (CHOOSE # FROM 1-" + (length-2) + ")");
			y = input.nextInt();

			if(board.checkMove(y,x,1))
			{
				x_remove = p1.getSpace()[0];
				y_remove = p1.getSpace()[1];

				p1.newSpace(x,y);

				board.remPiece(y_remove,x_remove);
				//board.addPiece(y,x);
				System.out.println(board.getBoard());
				System.out.println(board.getScore());
			}
			else
			{
				System.out.println("INVALID MOVE");
			}
		}
	}
}//GameBoard
