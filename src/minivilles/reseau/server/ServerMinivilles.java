import java.net.ServerSocket;

public class ServerMinivilles
{
	private static final int MAX_J = 4;

	private ServerSocket serv;

	public static ServerMinivilles createServ(int port)
	{
		try {
			ServerSocket serv = new ServerSocket(port);
			return new ServerMinivilles(serv);
		}
		catch (Exception e) { return null; }
	}

	private ServerMinivilles(ServerSocket serv)
	{
		this.serv = serv;
		AcceptClient ac = new AcceptClient();
		Thread tAc = new Thread();
		tAc.run();
	}

	class AcceptClient extends Runnable
	{
		private AcceptClient()
		{

		}

		public void run()
		{

		}
	}
}
