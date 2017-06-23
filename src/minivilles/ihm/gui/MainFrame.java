package minivilles.ihm.gui;

import minivilles.Controleur;
import minivilles.util.Utility;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;


public class MainFrame extends JFrame
{
	private Controleur 				controler;
	private JComponent				content;
	private JPanelWithBackground 	bgPanel;


	public MainFrame (Controleur controler)
	{
		super();
		// try
		// {
		// 	UIManager.setLookAndFeel( new SyntheticaBlackEyeLookAndFeel() );
		// } catch (Exception e) {}
		this.controler = controler;

		this.setTitle("MiniVilles");
		this.setSize(1200,700);
		this.setLocation(200,150);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);


		this.bgPanel = new JPanelWithBackground();
		this.add( bgPanel );
	}

	public Controleur 				getControler ()	{ return this.controler;	}
	public JComponent 				getContent ()	{ return this.content;		}
	public JPanelWithBackground 	getBgPanel ()	{ return this.bgPanel;		}

	public void openPage (JPanel panel)
	{
		this.content = panel;
		this.bgPanel.removeAll();

		this.bgPanel.add( panel );

		this.validate();
		this.repaint();
		this.setVisible(true);
	}



	public class JPanelWithBackground extends JPanel
	{
		private Image bgImg;


		public JPanelWithBackground ()
		{
			this.setLayout( new GridBagLayout() );
		}

		public void setImage (String fileName)
		throws IOException
		{
			if (fileName == null || fileName.equals(""))
				bgImg = null;
			else
				bgImg = Utility.getScaledImage( ImageIO.read( new File(fileName) ), 1200, 700);
		}

		public void paintComponent (Graphics g)
		{
			super.paintComponent(g);

			if (this.bgImg != null)
				g.drawImage( bgImg, 0, 0, this );
		}
	}
}


