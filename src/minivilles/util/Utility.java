package minivilles.util;

import minivilles.ihm.gui.MainFrame;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;



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
	
	public static int[] rotateAround (double centerX, double centerY, double x, double y, double rot)
	{
		double x1 = x - centerX;
		double y1 = y - centerY;

		double x2 = x1 * Math.cos(rot) - y1 * Math.sin(rot);
		double y2 = x1 * Math.sin(rot) + y1 * Math.cos(rot);

		return new int[] { (int) (x2 + centerX), (int) (y2 + centerY) };
	}

	public static Image getScaledImage (Image srcImg, int w, int h)
	{
		BufferedImage 	resizedImg	= new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D 		g2 			= resizedImg.createGraphics();

		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	public static int[] getPercentOfFrame (MainFrame frame, double pctW, double pctH)
	{
		return new int[] { (int) (frame.getWidth() * (pctW/100)), (int) (frame.getHeight() * (pctH/100)) };
	}

	public static Image resizeAfterFrame (Image srcImg, MainFrame frame, double pctW, double pctH)
	{
		int[] dim = Utility.getPercentOfFrame(frame, pctW, pctH);

		BufferedImage 	resizedImg	= new BufferedImage(dim[0], dim[1], BufferedImage.TYPE_INT_ARGB);
		Graphics2D 		g2 			= resizedImg.createGraphics();

		g2.drawImage(srcImg, 0, 0, dim[0], dim[1], null);
		g2.dispose();

		return resizedImg;
	}

	public static int arraySum (int[] arr)
	{
		int sum = 0;
		for (int val : arr)
			sum += val;

		return sum;
	}
}
