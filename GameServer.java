//GameServer.java
//Creates a game server, using the MTChat/Nim ideas
//Receives moves from the client
//GameServer.java
//Author(s): Elizabeth Wang, Sarah Chong
//Adapted from https://github.com/ChapmanCPSC353/mtchat

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameServer
{
	// Maintain list of all client sockets for broadcast
	private ArrayList<Socket> socketList;
	public static Board2 board;
	private static int score1 = 0, score2 = 0, score3 = 0, score4 = 0;
	public static String scoreboard1 ="", scoreboard2="", scoreboard3="", scoreboard4="";
	
	public GameServer()
	{
		socketList = new ArrayList<Socket>();
		board = new Board2(10);
	}

	public static void keepScore(int playerscore, char playernum)
	{
		System.out.println("Got to keepscore method");
		if(playernum == '1')
		{
			score1 = playerscore;
		}
		else if(playernum == '2')
		{
			score2 = playerscore;
		}
		else if(playernum == '3')
		{
			score3 = playerscore;
		}
		else if(playernum == '4')
		{
			score4 = playerscore;
		}
		
		System.out.println(score1);

		scoreboard1 = "Player 1 score: " + score1 + "\n";
		scoreboard2 = "Player 2 score: " + score2 + "\n";
		scoreboard3 = "Player 3 score: " + score3 + "\n";
		scoreboard4 = "Player 4 score: " + score4 + "\n";
	}
	public static String checkWinner()
	{
		int[] list = {score1,score2,score3,score4};
		
		int max = 0;
		
		for(int i : list)
		{
			if(i > max)
			{
				max = i; //make current value to highest
			}
		}
		
		if(score1 == max)
			return "Player 1 is the Winner";
		else if(score2 == max)
			return "Player 2 is the Winner";
		else if(score3 == max)
			return "Player 3 is the Winner";
		else if(score4 == max)
			return "Player 4 is the Winner";
		else
			return "There's a tie";
	}
	public static String printScore()
	{
		String scoreboard = scoreboard1 + scoreboard2 + scoreboard3 + scoreboard4;
		return scoreboard;
	}

	private void getConnection()
	{
		// Wait for a connection from the client
		try
		{
			System.out.println("Waiting for client connections on port 7654.");
			ServerSocket serverSock = new ServerSocket(7654);
			// This is an infinite loop, the user will have to shut it down
			// using control-c
			while (socketList.size() < 4)
			{
				Socket connectionSock = serverSock.accept();
				// Add this socket to the list
				socketList.add(connectionSock);
				// Send to ClientHandler the socket and arraylist of all sockets
				ClientHandler handler = new ClientHandler(connectionSock, this.socketList);
				Thread theThread = new Thread(handler);
				theThread.start();
			}
			
			serverSock.close();
		}
		catch (IOException e)
		{
			//System.out.println("This is GameServer.java");
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		GameServer server = new GameServer();
		//server.keepScore(5,'1');
		//System.out.println(server.printScore());
		server.getConnection();	

	}
} // GameServer
