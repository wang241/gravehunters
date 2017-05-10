//Board2.java
//Testing board
//Author: Elizabeth Wang

import java.util.*;

public class Board2
{
	public char[][] field; //Current board
	public int len; //size of the grid
	private int numO; //number of obstacles
	private int numP; //number of points

	Board2(int length) //Generates a board
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
	}//AddItemRan
	//Helper method to AddItemRan
	//Checks if space exists on the board
	public boolean checkSpace(int num)
	{
		if( num < 0 || num > len )
		{
			return false;
		}
		return true;
	}
	
	//Provides a way of accessing the player's position in reference to its own.
	public boolean checkMove(int new_x, int new_y, PlayerPiece2 player)
	{
		int x = player.getSpace()[0];
		int y = player.getSpace()[1];

		if(new_x >= 0 && new_y >= 0 && new_x < len && new_y < len)
		{
			if(field[new_y][new_x] == '*')
			{
				//adds one to player
				player.score();
				return true;
			}
			else if(field[new_y][new_x] == '-')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else //false if the distance is farther than 1
		{
			return false;
		}
	}
	//Makes a move based on a cardinal direction
	public void move(String command, PlayerPiece2 p)
	{
		//Store the old spaces, convert the string
		int old_x = p.getSpace()[0];
		int old_y = p.getSpace()[1];
		String comm = command.toLowerCase();
		
		//Find instances of these approved words for movement
		if(comm.indexOf("down") >= 0)
		{
			int new_x = old_x + 0;
			int new_y = old_y +1;
			
			//Check if the move is valid
			if(checkMove(new_x, new_y, p))
			{
				field[new_y][new_x] = p.getPiece();
				field[old_y][old_x] = '-';
				p.newSpace(new_x, new_y); //Update position
			}
			else
				System.out.println("Invalid move.");
		}
		else if(comm.indexOf("left") >= 0)
		{
			int new_x = old_x - 1;
			int new_y = old_y + 0;
			
			if(checkMove(new_x, new_y, p))
			{
				field[new_y][new_x] = p.getPiece();
				field[old_y][old_x] = '-';
				p.newSpace(new_x, new_y); //Update position
			}
			else
				System.out.println("Invalid move.");
		}
		else if(comm.indexOf("right") >= 0)
		{
			int new_x = old_x + 1;
			int new_y = old_y + 0;
			
			if(checkMove(new_x, new_y, p))
			{
				field[new_y][new_x] = p.getPiece();
				field[old_y][old_x] = '-';
				p.newSpace(new_x, new_y); //Update position
			}
			else
				System.out.println("Invalid move.");
		}
		else if(comm.indexOf("up") >= 0)
		{
			int new_x = old_x + 0;
			int new_y = old_y - 1;
			
			if(checkMove(new_x, new_y, p))
			{
				field[new_y][new_x] = p.getPiece();
				field[old_y][old_x] = '-';
				p.newSpace(new_x, new_y); //Update position
			}
			else
				System.out.println("Invalid move.");
		}
		else //If the word isn't recognized, the move fails.
			System.out.println("Invalid move.");
	}
	//Directly accesses and alters the board
	public void addPiece(int x, int y, PlayerPiece2 player)
	{
		field[y][x] = player.getPiece();
	}
	//Checks if all/majority of the points have been collected
	public boolean gameOver(PlayerPiece2 p1, PlayerPiece2 p2)
	{
		int total = p1.getScore() + p2.getScore();
		if(total == numP)
			return true;
		return false;
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
	public static void main(String[] args)
	{
		int len = 10;
		PlayerPiece2 p1 = new PlayerPiece2(1, 1, '1');
		PlayerPiece2 p2 = new PlayerPiece2(len-2, len-2, '2');
		
		Board2 board = new Board2(len);
		
		board.addPiece(1,1,p1);
		
		Scanner input = new Scanner(System.in);
		
		int turn = 0;
		
		while(!board.gameOver(p1, p2))
		{
			System.out.println(board.getBoard());
			System.out.println(p1.printScore());
			
			System.out.println("Enter a cardinal direction.");
			String temp = input.next();
			
			board.move(temp, p1);
		}
		System.out.println("Game Over, all souls collected. Good job!");
	}
}