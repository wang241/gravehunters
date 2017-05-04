//GameServer.java
//Creates a game server, using the MTChat/Nim ideas
//Receives moves from the client
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

	public GameServer()
	{
		socketList = new ArrayList<Socket>();
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
			while (true)
			{
				Socket connectionSock = serverSock.accept();
				// Add this socket to the list
				socketList.add(connectionSock);
				// Send to ClientHandler the socket and arraylist of all sockets
				ClientHandler handler = new ClientHandler(connectionSock, this.socketList);
				Thread theThread = new Thread(handler);
				theThread.start();
			}
			// Will never get here, but if the above loop is given
			// an exit condition then we'll go ahead and close the socket
			//serverSock.close();
		}
		catch (IOException e)
		{
			System.out.println("This is GameServer.java");
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		GameServer server = new GameServer();
		server.getConnection();
	}
} // GameServer