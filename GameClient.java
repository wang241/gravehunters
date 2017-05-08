//GameClient.java
//Adapted from https://github.com/ChapmanCPSC353/mtchat/

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class GameClient
{
	public static void main(String[] args)
	{
		try
		{
			String hostname = "127.0.0.1";
			int port = 7654;

			System.out.println("Connecting to server on port " + port);
			Socket connectionSock = new Socket(hostname, port);

			DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());

			System.out.println("Connection made.");

			// Start a thread to listen and display data sent by the server
			ClientListener listener = new ClientListener(connectionSock);
			Thread theThread = new Thread(listener);
			theThread.start();

			// Read input from the keyboard and send it to everyone else.
			Scanner keyboard = new Scanner(System.in);
			while (true)
			{
				String data = keyboard.nextLine();
				serverOutput.writeBytes(data + "\n");
				if(data.toLowerCase().indexOf("quit") != -1)//Quit code
				{
					System.out.println("Closed by user" + connectionSock);
					connectionSock.close();
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("This is GameClient.java");
			System.out.println(e.getMessage());
		}
	}
} //GameClient
