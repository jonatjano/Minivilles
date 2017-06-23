package minivilles.ihm.gui;

import minivilles.util.Utility;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class JCanvas extends JPanel
{
	private LinkedList<DessinString> 	stringList = new LinkedList<DessinString>();
	private LinkedList<DessinImage> 	imageList  = new LinkedList<DessinImage>();


	public JCanvas (Dimension dim)
	{
		this.setPreferredSize( dim );
	}


	@Override
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		// g.setColor( Color.WHITE );//new Color( (float) Math.random(), (float) Math.random(), (float) Math.random()) );
		// g.fillRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
		g.clearRect( 0, 0, this.getWidth(), this.getHeight() );

		g.setColor( Color.BLACK );

		// Dessin des labels
		for (DessinString ds : this.stringList)
		{
			g.drawString(ds.getVal(), ds.getX(), ds.getY());	
		}

		// Dessins des images
		for (DessinImage di : this.imageList)
		{
			BufferedImage img = di.getImage();
			if ( di.getDimSrc() != null )
				img = img.getSubimage( di.getSrcX(), di.getSrcY(), (int) di.getDimSrc().getWidth(), (int) di.getDimSrc().getHeight() );


			if ( di.getDim() != null )
				g.drawImage( img, di.getX(), di.getY(), (int) di.getDim().getWidth(), (int) di.getDim().getHeight(), null );
			else
				g.drawImage( img, di.getX(), di.getY(), null );
		}
	}

	public void clearAll ()
	{
		// this.stringList = new LinkedList<DessinString>();
		// this.imageList = new LinkedList<DessinImage>();
		this.stringList.clear();
		this.imageList.clear();

		this.repaint();			
	}

	public void printString (String val, int x, int y)
	{
		this.stringList.add( new DessinString(val, x, y) );
		repaint();
	}

	public void printImage (BufferedImage img, Dimension dim, int x, int y)
	{
		this.imageList.add( new DessinImage(img, dim, x, y) );
		repaint();
	}

	public void printImage (BufferedImage img, Dimension dim, int srcX, int srcY, Dimension dimSrc, int x, int y)
	{
		this.imageList.add( new DessinImage(img, dim, srcX, srcY, dimSrc, x, y) );
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

		public BufferedImage 	getImage () 	{ return this.img; 		}
		public Dimension	 	getDim () 		{ return this.dim; 		}
		public Dimension	 	getDimSrc () 	{ return this.dimSrc; 	}
		public int				getX () 		{ return this.x; 		}
		public int	 			getY () 		{ return this.y; 		}
		public int	 			getSrcX () 		{ return this.srcX; 	}
		public int	 			getSrcY () 		{ return this.srcY; 	}
	}
}