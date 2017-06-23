package minivilles.reseau;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Client implements Runnable
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
			Client c = new Client(sock, is, os);
			Thread tClient = new Thread(c);
			tClient.start();
			return c;
		}
		catch (Exception e) { return null; }
	}

	private Client(Socket sock, InputStream is, OutputStream os)
	{
		this.sock = sock;
		this.is = is;
		this.os = os;
	}

	public void run()
	{
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
			os.flush()
		}
		catch (Exception e) { System.out.println("echec a l'envoi de " + msg); }
	}

	private void actionOnMsg(String msg)
	{
		System.out.println(msg);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Client c = connect("localhost", 7777);
		if (c == null) {
			System.out.println("Ce serveur est inaccessible");
		}
		while (sc.hasNextLine()) {
			c.sendMsg(sc.nextLine());
		}
	}
}
