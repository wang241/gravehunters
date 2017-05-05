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
			while (true)
			{
				// Get data sent from a client
				String clientText = clientInput.readLine();
				if (clientText != null)
				{
					//Allows for server log to know which player is inputting what.
					System.out.println("From " + player + ": " + clientText);
					// Turn around and output this data
					// to all other clients except the one
					// that sent us this information
					for (Socket s : socketList)
					{
						if (s != connectionSock)
						{
							DataOutputStream clientOutput = new DataOutputStream(s.getOutputStream());
							clientOutput.writeBytes("Player " + player + ": " + clientText + "\n");
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