//ClientHandler.java
//Adapted from https://github.com/ChapmanCPSC353/mtchat

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ClientHandler implements Runnable
{
	private Socket connectionSock = null;
	private ArrayList<Socket> socketList;
	private int player;
	private int length = 10;
	public PlayerPiece2 p;


	ClientHandler(Socket sock, ArrayList<Socket> socketList)
	{
		player = socketList.size(); //Assigns player number via order socket list.
		this.connectionSock = sock;
		this.socketList = socketList;	// Keep reference to master list
	}

	public char getPlayer()
	{
		char temp = (char)player;
		return temp;
	}
	public void run()
	{
        		// Get data from a client and send it to everyone else
		try
		{
			System.out.println("Connection made with socket " + connectionSock);
			BufferedReader clientInput = new BufferedReader(
				new InputStreamReader(connectionSock.getInputStream()));

			switch(player)
			{
				case 1: p = new PlayerPiece2(1, 1, '1');
						break;
				case 2: p = new PlayerPiece2(length - 2, length - 2, '2');
						break;
				case 3: p = new PlayerPiece2(1, length - 2, '3');
						break;
				case 4: p = new PlayerPiece2(length - 2, 1, '4');
						break;
			}

			GameServer.board.addPiece(p.getSpace()[0], p.getSpace()[1], p);
			
			boolean value = true;
			while (value)//!GameServer.board.gameOver()) //While the board still has '*'
			{
				//Game loop starts here
				DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
				clientOutput.writeBytes(GameServer.board.getBoard()+ "\n");
				clientOutput.writeBytes(p.printScore()+ "\n");
				clientOutput.writeBytes("Up, Down, Left, Right?\n");
				// Get data sent from a client
				String clientText = clientInput.readLine();
				if (clientText != null)
				{
					GameServer.board.move(clientText,p);
					//Allows for server log to know which player is inputting what.
					System.out.println("From " + player + ": " + clientText);
					// Turn around and output this data
					// to all other clients except the one
					// that sent us this information
					for (Socket s : socketList)
					{
						if(GameServer.board.gameOver())
						{
							clientOutput.writeBytes("End of game. Type quit.\n");
							value = false;
						}
						if (s != connectionSock)
						{
							clientOutput = new DataOutputStream(s.getOutputStream());
							clientOutput.writeBytes(GameServer.board.getBoard() + "\n");
							clientOutput.writeBytes(p.printScore() + "\n");
							clientOutput.writeBytes("Up, Down, Left, Right?\n");
							//clientOutput.writeBytes("Player " + player + ": " + clientText + "\n");
						}
					}
				}
				else
				{
				  // Connection was lost
				  System.out.println("Closing connection for socket " + connectionSock);
				   // Remove from arraylist
				   socketList.remove(connectionSock);
				   connectionSock.close();
				   break;
				}
			}//end of while loop
			DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
			for(Socket s : socketList)
			{
				clientOutput.writeBytes("All souls collected. Type \"quit.\" Good job!\n");
			}
		}
		catch (Exception e)
		{
			System.out.println("This is ClientHandler.java");
			System.out.println("Error: " + e.toString());
			// Remove from arraylist
			socketList.remove(connectionSock);
		}
	}
} // ClientHandler for GameServer
