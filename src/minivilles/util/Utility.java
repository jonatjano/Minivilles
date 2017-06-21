package minivilles.util;


public class Utility
{
	public static void waitForSeconds (double sec)
	{
		try						{ Thread.sleep( (long) (sec * 1000) ); }
		catch (Exception e)		{}
	}

	public static int posModulo (int val, int mod)
	{
		while (val < 0)
			val += mod;

		return val;
	}
}
