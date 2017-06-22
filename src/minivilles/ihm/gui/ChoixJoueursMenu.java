package minivilles.ihm.gui;

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

		this.lines		= new ChoixJoueursLine[ ServeurMinivilles.MAX_J ];
		this.line[0] 	= new ChoixJoueursLine();

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


