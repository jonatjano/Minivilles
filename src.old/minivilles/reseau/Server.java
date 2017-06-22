package minivilles.reseau;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Server
{
	public static final int MAX_J = 4;

	private ServerSocket serv;


	public static Server createServ(int port)
	{
		try
		{
			ServerSocket serv = new ServerSocket(port);
			return new Server(serv);
		}
		catch (Exception e) { return null; }
	}

	private Server(ServerSocket serv)
	{
		this.serv = serv;
		AcceptClient ac = new AcceptClient();
		Thread tAc = new Thread(ac);
		tAc.run();
	}

	class AcceptClient implements Runnable
	{
		private LinkedList<ServerClient> clientList;
		private boolean stop;

		private AcceptClient() {}

		public void run()
		{
			Socket sock = null;
			stop = false;
			while (!stop) {
				try {
					sock = serv.accept();
					InputStream is = sock.getInputStream();
					OutputStream os = sock.getOutputStream();
					clientList.add(new ServerClient(sock, is, os));
				}
				catch (Exception e) {
					try {sock.close();} catch (Exception ex) {}
				}
			}
		}

		public void stop()
		{
			stop = true;
		}
	}

	class ServerClient
	{
		OutputStream os;

		public ServerClient(Socket sock, InputStream is, OutputStream os)
		{
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
	}

	private void actionOnMsg(String msg)
	{
		System.out.println("TODO : Client.java line 50");
	}
}
