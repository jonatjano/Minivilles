package minivilles.reseau;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

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
		private boolean continue;

		private AcceptClient() {}

		public void run()
		{
			Socket sock;
			continue = true;
			while (continue) {
				sock = serv.accept();
				try {
					InputStream is = sock.getInputStream();
					OutputStream os = sock.getOutputStream();
					clientList.add(new ServerClient(sock, is, os));
				}
				catch (Exception e) {
					sock.close();
				}
			}
		}

		public void stop()
		{
			continue = false;
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
				System.out.println("TODO : ServerMinivilles#ServerClient.ServerClient() line 75");
				//actionOnMsg(msg);
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
}
