package minivilles.ihm.gui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class ChoixJoueursLine extends JPanel implements;
{
	private JButton addB;


	public ChoixJoueursLine (MainFrame frame, boolean bool)
	{
		this.addB 	= new JButton("Le bouton");
		this.addB.addActionListener( this );
		this.add( addB );


        this.setVisible(true);
        this.addB.setVisible(bool);
	}

	// public void actionPerformed (ActionEvent e)
	// {
	// 	if 		( e.getSource() == this.launchB )
	// 		this.frame.getControler().getIhm().displayChoixJoueurs();
	// 	else if ( e.getSource() == this.quittB )
	// 		System.exit(0);
	// }
}


