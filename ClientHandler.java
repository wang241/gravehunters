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
	public boolean gameOver;
	public boolean first;
	public boolean turn;

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

				//assigns players to the game
			if (GameServer.FirstPlayer()==true)
			{
				DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
				clientOutput.writeBytes(GameServer.gb.getBoard());
				clientOutput.writeBytes("You are player 1" + "\n");
				first = true;
			}

			else
			{
				DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
				clientOutput.writeBytes(GameServer.gb.getBoard());
				clientOutput.writeBytes("You are player 2" + "\n");
				first = false;
			}

			while (true)
			{
				// Get data sent from a client
				String clientText = null;

				while(true)
				{
					if (GameServer.getTurn()==first)
					{
					DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
					clientOutput.writeBytes("It is your turn" + "\n");
					}
					
					clientText = clientInput.readLine();
					
					int x_axis = Character.getNumericValue(clientText.charAt(0));
					int y_axis = Character.getNumericValue(clientText.charAt(2));
					
					GameServer.gb.addPiece(x_axis, y_axis);
					
					if (GameServer.getTurn()==first)
					{
						break;
					}

					else
					{
						DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
						clientOutput.writeBytes("Wait your turn" + "\n");
					}
				}
				gameOver = GameServer.gb.gameOver();

				if (clientText != null)
				{
					System.out.println("Received: " + clientText);

					if(gameOver == false)
					{
						boolean check = false;

						if(check == false)
						{
							DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
							clientOutput.writeBytes("Please enter a proper input" + "\n");
							clientText = clientInput.readLine();
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
				}

					// Turn around and output this data
					// to all other clients except the one
					// that sent us this information
					for (Socket s : socketList)
					{
						gameOver = GameServer.gb.gameOver();

						if(gameOver == false)
						{
							DataOutputStream clientOutput = new DataOutputStream(s.getOutputStream());
							clientOutput.writeBytes(GameServer.gb.getBoard());
						}

						else if (gameOver == true)
						{
							DataOutputStream clientOutput = new DataOutputStream(s.getOutputStream());
							clientOutput.writeBytes("The game is over congrats to the victor" + "\n");
						}
					}
					GameServer.Turn();

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
