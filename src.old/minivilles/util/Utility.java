package minivilles.util;

import java.util.ArrayList;


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

	public static boolean containsIgnoreCase (ArrayList<String> ar, String s)
	{
		for ( String sTemp : ar)
			if ( sTemp.equalsIgnoreCase(s) )
				return true;
				
		return false;
	}
	
	
}
