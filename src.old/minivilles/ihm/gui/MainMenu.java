package minivilles.ihm.gui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class MainMenu extends JPanel implements ActionListener
{
	private MainFrame 	frame;
	private JButton 	quittB, launchB, joinB;


	public MainMenu (MainFrame frame)
	{
		this.frame = frame;
		this.frame.setLayout( new GridBagLayout() );
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );


		this.launchB 	= new JButton("Lancer une partie");
		this.launchB.addActionListener( this );
		this.add( launchB );

		this.joinB 		= new JButton("Rejoindre");
		this.joinB.addActionListener( this );
		this.add( joinB );

		this.quittB 	= new JButton("Quitter");
		this.quittB.addActionListener( this );
		this.add( quittB );


        this.setVisible(true);
	}

	public void actionPerformed (ActionEvent e)
	{
		if 		( e.getSource() == this.launchB )
			this.frame.getControler().getIhm().displayChoixJoueurs();
		else if ( e.getSource() == this.quittB )
			System.exit(0);
	}
}


