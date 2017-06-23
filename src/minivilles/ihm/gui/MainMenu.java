package minivilles.ihm.gui;

import minivilles.util.Utility;
import java.io.IOException;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class MainMenu extends JPanel implements ActionListener
{
	private MainFrame 	frame;
	private JButton 	quittB, launchB, joinB;
	private JLabel		logoL;
	private ImageIcon	logo;


	public MainMenu (MainFrame frame)
	{
		this.frame = frame;
		// this.frame.getBgPanel().setLayout( new GridBagLayout() );
		// try						{ this.frame.getBgPanel().setImage("../images/minivilles.jpg"); }
		// catch (IOException e) 	{}



		JPanel mainPanel = new JPanel();
		this.setLayout( new GridBagLayout() );

		GridBagConstraints c = new GridBagConstraints();


		// Logo de MiniVilles
		// BufferedImage img = new ImageIcon("../images/logo.png").getImage();
		// Image resized = Utility.getScaledImage(new ImageIcon("../images/logo.png").getImage(), 600, 262);
		ImageIcon logo = new ImageIcon( Utility.getScaledImage(new ImageIcon("../images/logo.png").getImage(), 600, 262) );
		this.logoL = new JLabel ("");
		this.logoL.setIcon( logo );

		c.gridwidth = 2;
		this.add( logoL, c );
		// this.logoL.setVisible(false);


		JPanel buttonP = new JPanel();
		buttonP.setLayout( new GridLayout(3, 1, 0, 10) );

		this.launchB 	= new JButton("Lancer une partie");
		this.launchB.addActionListener( this );
		buttonP.add( launchB );

		this.joinB 		= new JButton("Rejoindre");
		this.joinB.addActionListener( this );
		buttonP.add( joinB );

		this.quittB 	= new JButton("Quitter");
		this.quittB.addActionListener( this );
		buttonP.add( quittB );

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add( buttonP, c );


        this.setVisible(true);
	}

	public void actionPerformed (ActionEvent e)
	{
		if 		( e.getSource() == this.launchB )
			this.frame.getControler().getIhm().displayChoixJoueurs();
		else if	( e.getSource() == this.joinB )
			this.frame.getControler().getIhm().displayChoixJoueurs();
		else if ( e.getSource() == this.quittB )
			System.exit(0);
	}
}


