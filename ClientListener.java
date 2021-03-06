//ClientListener.java
//Adapted from https://github.com/ChapmanCPSC353/mtchat

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ClientListener implements Runnable
{
	private Socket connectionSock = null;
	private int state = 0;

	ClientListener(Socket sock)
	{
		this.connectionSock = sock;
	}

	public void run()
	{
       		 // Wait for data from the server.  If received, output it.
		try
		{			
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			
			while (true)
			{
				// Get data sent from the server
				String serverText = serverInput.readLine();
				if (serverInput != null)
				{
					System.out.println(serverText);
				}
				else
				{
					// Connection was lost
					System.out.println("Closing connection for socket " + connectionSock);
					connectionSock.close();
					break;
				}
			}
		}
		catch (Exception e)
		{
			//System.out.println("This is ClientListener.java");
			//System.out.println("Error: " + e.toString());
			System.out.println("Press enter to quit.");
		}
	}
} // ClientListener for GameClient