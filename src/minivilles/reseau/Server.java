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
	private AcceptClient acceptCli;

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
		acceptCli = new AcceptClient();
		Thread tAcceptCli = new Thread(acceptCli);
		tAcceptCli.start();
	}

	class AcceptClient implements Runnable
	{
		private LinkedList<ServerClient> clientList;
		private boolean stop;

		private AcceptClient() {
			clientList = new LinkedList<ServerClient>();
		}

		public void run()
		{
			Socket sock = null;
			stop = false;
			while (!stop) {
				try {
					sock = serv.accept();
					InputStream is = sock.getInputStream();
					OutputStream os = sock.getOutputStream();
					ServerClient c = new ServerClient(sock, is, os);
					Thread tClient = new Thread(c);
					tClient.start();
					clientList.add(c);
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

		private LinkedList<ServerClient> getClientList()
		{
			return clientList;
		}
	}

	class ServerClient implements Runnable
	{
		private OutputStream os;
		private InputStream is;

		public ServerClient(Socket sock, InputStream is, OutputStream os)
		{
			this.os = os;
			this.is = is;
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
				os.flush();
			}
			catch (Exception e) {}
		}
	}

	private void actionOnMsg(String msg)
	{
		for (ServerClient sc : acceptCli.getClientList()) {
			sc.sendMsg(msg);
		}
	}

	public static void main(String[] args) {
		Server s = createServ(7777);
		System.out.println(s);
	}
}
