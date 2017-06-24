package minivilles.ihm.gui;

import minivilles.Controleur;
import minivilles.util.Utility;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;


public class MainFrame extends JFrame
{
	private Controleur 				controler;
	private JComponent				content;
	private JPanelWithBackground 	bgPanel;
	private JOptionPane				errorMsg;

	private Dimension				screenSize;

	private Font 					font;


	public MainFrame (Controleur controler)
	{
		super();
		
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Police des panel
		this.font = new Font("Arial", 0, 16);

		this.controler = controler;
		this.setTitle("MiniVilles");

		this.setSize(1300, 900);
		this.setLocation(200, 100);
		// this.setExtendedState( JFrame.MAXIMIZED_BOTH );
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );

		this.setVisible(true);
		this.bgPanel 	= new JPanelWithBackground( this );
		this.errorMsg 	= new JOptionPane( new JLabel("", JLabel.CENTER) );


		
		this.setResizable(false);
		// Ajoute un évènement quand la fenêtre est redimensionnée
		// this.addComponentListener(new ComponentAdapter() {
		// 	private JPanelWithBackground bgPanel;

  //           public void componentResized (ComponentEvent e)
  //           {
  //           	try 					{ this.bgPanel.resizeImg(); }
  //           	catch (IOException ex) 	{}

  //           	// Resize tout le CONTENU
  //           }

  //           private ComponentAdapter init(JPanelWithBackground bgPanel)
  //           {
  //           	this.bgPanel = bgPanel;
  //           	return this;
  //           }
  //       }.init(this.bgPanel) );

		this.add( bgPanel );
	}

	public Controleur 				getControler ()		{ return this.controler;	}
	public JComponent 				getContent ()		{ return this.content;		}
	public JPanelWithBackground 	getBgPanel ()		{ return this.bgPanel;		}
	public Font 					getFont () 			{ return this.font;			}
	public Dimension				getScreenSize ()	{ return this.screenSize;	}

	public void openPage (JPanel panel)
	{
		this.content = panel;
		this.bgPanel.removeAll();

		this.bgPanel.add( panel );

		this.validate();
		this.repaint();
		this.setVisible(true);
	}

	public void errorMessage (String title, String str, int val)
	{
		this.errorMsg.showMessageDialog( this, str, title, val);
	}



	public class JPanelWithBackground extends JPanel
	{
		private Image 		bgImg;
		private Image 		toDisplayImg;
		private MainFrame	frame;


		public JPanelWithBackground (MainFrame frame)
		{
			this.frame = frame;
			this.setLayout( new GridBagLayout() );
		}

		public void setImage (String fileName)
		throws IOException
		{
			if (fileName == null || fileName.equals(""))
			{
				this.bgImg 			= null;
				this.toDisplayImg 	= null;
			}
			else
			{
				this.bgImg 			= ImageIO.read( new File(fileName) );
				this.toDisplayImg 	= Utility.getScaledImage( bgImg, this.frame.getWidth(), this.frame.getHeight() );
			}
		}

		public void resizeImg ()
		throws IOException
		{
			this.toDisplayImg = Utility.getScaledImage( this.bgImg, this.frame.getWidth(), this.frame.getHeight() );
		}

		public void paintComponent (Graphics g)
		{
			super.paintComponent(g);

			if (this.toDisplayImg != null)
				g.drawImage( this.toDisplayImg, 0, 0, this );
		}
	}
}


