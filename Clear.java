public class Clear
{
	public static void main(String[] args)
	{
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
		for (int i = 0;i < 20 ;i++ )
		{
			System.out.println("coucou");
		}
		try {
			pb.inheritIO().start().waitFor();
		}
		catch (Exception e) {
			e.printStackTrace();
		}


	}
}
