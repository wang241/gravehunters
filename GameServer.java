//GameServer.java
//Creates a game server, using the MTChat/Nim ideas
//Receives moves from the client
//Author(s): Elizabeth Wang, Sarah Chong

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameServer
{
	//A list of all client sockets
	private ArrayList<socket> socketList;
	
	public GameServer()
	{
		
	}
}