package minivilles.reseau;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Client
{
	private Socket sock;
	private InputStream is;
	private OutputStream os;

	public static Client connect(String ip, int port)
	{
		try
		{
			Socket sock = new Socket(ip, port);
			InputStream is = sock.getInputStream();
			OutputStream os = sock.getOutputStream();
			return new Client(sock, is, os);
		}
		catch (Exception e) { return null; }
	}

	private Client(Socket sock, InputStream is, OutputStream os)
	{
		this.sock = sock;
		this.is = is;
		this.os = os;

		Scanner scis = new Scanner(is);

		while (scis.hasNextLine()) {
			String msg = scis.nextLine();
			actionOnMsg(msg);
		}
	}

	public void sendMsg(String msg)
	{
		try {
			os.write((msg + "\n").getBytes("UTF-8"));
			os.flush();
		}
		catch (Exception e) {}
	}

	private void actionOnMsg(String msg)
	{
		System.out.println("TODO : Client.java line 50");
	}
}
