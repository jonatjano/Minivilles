package minivilles.ihm.gui;

import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class JCanvas extends JPanel
{
	private Dimension					dim;
	private LinkedList<DessinString> 	stringList = new LinkedList<DessinString>();
	private LinkedList<DessinImage> 	imageList  = new LinkedList<DessinImage>();


	public JCanvas (Dimension dim)
	{
		this.dim = dim;
		this.setPreferredSize( this.dim );
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.setColor( Color.WHITE );
		g.fillRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());

		g.setColor( Color.BLACK );

		// Dessin des labels
		for (DessinString ds : stringList)
		{
			g.drawString(ds.getVal(), ds.getX(), ds.getY());	
		}

		// Dessins des images
		for (DessinImage di : imageList)
		{
			g.drawImage(di.getImage(), 0, 0, null);	
		}
	}

	public void printString (String val, int x, int y)
	{
		stringList.add( new DessinString(val, x, y) );
		repaint();
	}

	public void printImage (BufferedImage img, Dimension dim, int x, int y)
	{
		imageList.add( new DessinImage(img, dim, x, y) );
		repaint();
	}



	class DessinString
	{
		private String val;
		private int x;
		private int y;


		public DessinString(String val, int x, int y)
		{
			this.val 	= val;
			this.x 		= x;
			this.y 		= y;
		}

		public String 	getVal () 	{ return this.val; 	}
		public int		getX () 	{ return this.x; 	}
		public int	 	getY () 	{ return this.y; 	}
	}

	class DessinImage
	{
		private BufferedImage 	img;
		private Dimension		dim;
		private int x;
		private int y;


		public DessinImage (BufferedImage img, Dimension dim, int x, int y)
		{
			this.img = img;
			// REDIMENSIONNER
			this.dim = dim;
			this.x = (int) (x - this.dim.getWidth()/2);
			this.y = (int) (y - this.dim.getHeight()/2);
		}
		// Deuxième avec portion de l'image (xy début, xy fin)

		public BufferedImage 	getImage () { return this.img; 	}
	}
}