package minivilles.ihm.gui;

import minivilles.reseau.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class ChoixJoueursMenu extends JPanel
{
	private ChoixJoueursLine[]	lines;
	private MainFrame 			frame;


	public ChoixJoueursMenu (MainFrame frame)
	{
		this.frame = frame;

		this.lines		= new ChoixJoueursLine[ ServerMinivilles.MAX_J ];
		this.lines[0] 	= new ChoixJoueursLine(true);

        this.setVisible(true);
	}

	// public void actionPerformed (ActionEvent e)
	// {
	// 	if 		( e.getSource() == this.launchB )
	// 	{
	// 		this.controler.getIhm().displayChoixJoueurs();
	// 	}
	// 	else if ( e.getSource() == this.quittB )
	// 		System.exit(0);
	// }
}


