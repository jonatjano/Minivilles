package minivilles.util;


public class Utility
{
	public static void waitForSeconds (double sec)
	{
		try						{ Thread.sleep( (long) (sec * 1000) ); }
		catch (Exception e)		{}
	}
}
