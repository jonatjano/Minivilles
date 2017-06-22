package minivilles.ihm.gui;

import java.awt.*;
import javax.swing.*;


public class MainMenu extends JPanel
{
	private MainFrame 	frame;
	private JButton 	quittB, launchB;


	public MainMenu (MainFrame frame)
	{
		this.frame = frame;
		this.frame.setLayout( new GridBagLayout() );
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );

		this.quittB = new JButton("Quitter");
		this.add( quittB );

		this.launchB = new JButton("Lancer une partie");
		this.add( launchB );


        this.setVisible(true);
	}
}


