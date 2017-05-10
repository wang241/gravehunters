
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
	public int player;

	ClientHandler(Socket sock, ArrayList<Socket> socketList)
	{
		player = socketList.size();
		this.connectionSock = sock;
		this.socketList = socketList;
	}

	public void run()
	{
		try
		{
			System.out.println("Connection made with socket " + connectionSock);
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));

			//Initiate gameboard, start game logic in loop
			//DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
			//clientOutput.writeBytes(GameServer.gb.getBoard());

			while (true)
			{
				// GET DATA SENT FROM A CLIENT
				PlayerPiece p1 = new PlayerPiece(1,1,'1');
				PlayerPiece p2 = new PlayerPiece(9,9,'2');


				//GameServer.gb.addPiece();

				String clientText = clientInput.readLine();
				if (clientText != null)
				{
					//SENT TO SERVER
					System.out.println("From player " + player + ": " + clientText);

					// SEND TO EVERYONE
					for (Socket s : socketList)
					{
							DataOutputStream clientOutput = new DataOutputStream(s.getOutputStream());
							clientOutput.writeBytes("Player " + player + ": " + clientText + " " + "\n");
							clientOutput.writeBytes(GameServer.gb.getBoard());
					}

				}

				else
				{
				  	System.out.println("Closing connection for socket " + connectionSock);
				   	socketList.remove(connectionSock);
				   	connectionSock.close();
				   	break;
				}
			}


		}

		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
			// REMOVE FROM ARRAYLIST
			socketList.remove(connectionSock);
		}
	}
}

//ClientHandler for GameServer
