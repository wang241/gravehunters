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
			//PlayerPiece p1 = new PlayerPiece(1,1,'1');
			//PlayerPiece p2 = new PlayerPiece(9,9,'2');

			//board.addPiece('1','1',p1);
			//board.addPiece('9','9',p2);
			//System.out.println(GameServer.gb.getBoard());

			BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));

			while (true)
			{
				// Get data sent from the server
				String serverText = serverInput.readLine();
				if (serverInput != null)
				{
					//char x_axis = serverText.charAt(2);
					//char y_axis = serverText.charAt(0);

					//board.addPiece(x_axis,y_axis,p1);
					System.out.println(serverText);
					//System.out.println(GameServer.gb.getBoard());
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
			System.out.println("This is ClientListener.java");
			//System.out.println("Error: " + e.toString());
			System.out.println("Press enter to quit.");
		}
	}
} // ClientListener for GameClient
