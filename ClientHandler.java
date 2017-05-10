
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
		//INITIALIZATION OF PLAYER PIECES
		PlayerPiece p1 = new PlayerPiece(1,1,'1');
		PlayerPiece p2 = new PlayerPiece(1,8,'2');
		PlayerPiece p3 = new PlayerPiece(8,1,'3');
		PlayerPiece p4 = new PlayerPiece(8,8,'4');

		try
		{
			System.out.println("Connection made with socket " + connectionSock);
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));

			//DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
			//clientOutput.writeBytes(GameServer.gb.getBoard());

			while (true)
			{
				// GET DATA SENT FROM A CLIENT

				switch(player)
				{
					case 1: GameServer.gb.addPiece(p1.getSpace()[0],p1.getSpace()[1],p1.getPiece());
						break;
					case 2: GameServer.gb.addPiece(p2.getSpace()[0],p2.getSpace()[1],p2.getPiece());
						break;
					case 3: GameServer.gb.addPiece(p3.getSpace()[0],p3.getSpace()[1],p3.getPiece());
						break;
					case 4: GameServer.gb.addPiece(p4.getSpace()[0],p4.getSpace()[1],p4.getPiece());
						break;
				}

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
