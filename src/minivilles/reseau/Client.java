package minivilles.reseau;

import java.net.Socket;

public class Client
{
	private Socket sock;

	public static Client connect(String ip, int port)
	{
		try
		{
			Socket sock = new Socket(ip, port);
			return new Client(sock);
		}
		catch (Exception e) { return null; }
	}

	private Client(Socket sock)
	{

	}
}
