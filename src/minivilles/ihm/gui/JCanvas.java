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
	public void paintComponent (Graphics g)
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
			if 		( di.getDimSrc() != null )
				g.drawImage( di.getImage(), 0, 0, (int) di.getDim().getWidth(), (int) di.getDim().getHeight(), di.getSrcX(), di.getSrcY(), di.getSrcX() + (int) di.getDimSrc().getWidth(), di.getSrcY() + (int) di.getDimSrc().getHeight(), null );
			else if ( di.getDim() != null )
				g.drawImage( di.getImage(), di.getX(), di.getY(), (int) di.getDim().getWidth(), (int) di.getDim().getHeight(), null );
			else
				g.drawImage( di.getImage(), di.getX(), di.getY(), null );
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

	public void printImage (BufferedImage img, Dimension dim, int srcX, int srcY, Dimension dimSrc, int x, int y)
	{
		imageList.add( new DessinImage(img, dim, srcX, srcY, dimSrc, x, y) );
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
		private Dimension		dim, dimSrc;
		private int x, 		y,
					srcX, 	srcY;


		public DessinImage (BufferedImage img, Dimension dim, int x, int y)
		{
			this.img = img;
			this.dim = dim;

			this.x = (int) (x - ( (this.dim != null) ? this.dim.getWidth()/2 : img.getWidth()/2) );
			this.y = (int) (y - ( (this.dim != null) ? this.dim.getHeight()/2 : img.getHeight()/2) );

			this.dimSrc = null;
			this.srcX = this.srcY = -1;
		}

		public DessinImage (BufferedImage img, Dimension dim, int srcX, int srcY, Dimension dimSrc, int x, int y)
		{
			this.img = img;
			this.dim = dim;

			this.x = (int) (x - ( (this.dim != null) ? this.dim.getWidth()/2 : img.getWidth()/2) );
			this.y = (int) (y - ( (this.dim != null) ? this.dim.getHeight()/2 : img.getHeight()/2) );

			this.dimSrc = dimSrc;
			this.srcX = srcX;
			this.srcY = srcY;
		}

		public BufferedImage 	getImage () 	{ return this.img; 	}
		public Dimension	 	getDim () 		{ return this.dim; 	}
		public Dimension	 	getDimSrc () 	{ return this.dim; 	}
		public int				getX () 		{ return this.x; 	}
		public int	 			getY () 		{ return this.y; 	}
		public int	 			getSrcX () 		{ return this.srcX; 	}
		public int	 			getSrcY () 		{ return this.srcY; 	}
	}
}