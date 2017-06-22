package minivilles.ihm.gui;

import minivilles.reseau.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class ChoixJoueursMenu extends JPanel implements ActionListener
{
	private ChoixJoueursLine[]	lines;
	private	JButton				launchB,
								retourB;
	private MainFrame 			frame;


	public ChoixJoueursMenu (MainFrame frame)
	{
		this.frame = frame;
		int maxJ = ServerMinivilles.MAX_J;
		this.setLayout( new GridLayout(maxJ + 2, 1) );

		this.lines		= new ChoixJoueursLine[ maxJ ];

		for (int i = 0; i < maxJ; i++)
		{
			this.lines[i] 	= new ChoixJoueursLine(this, (i+1));
			this.add( this.lines[i] );
		}


		/* Boutons du bas */
		this.launchB 	= new JButton("Lancer");
		this.launchB.addActionListener( this );
		this.add( launchB );

		this.retourB 	= new JButton("Retour");
		this.retourB.addActionListener( this );
		this.add( retourB );


        this.setVisible(true);
	}

	public void actionPerformed (ActionEvent e)
	{
		if 		( e.getSource() == this.launchB )
		{
			ArrayList<String> names = new ArrayList<String>();
			for (ChoixJoueursLine line : lines)
			{
				if ( line.getText().matches(".*[a-zA-Z].*") )
					names.add( line.getText() );
				// else if ( !line.equals("") )
				// 	label.setErrorText("Erreur : saisie incorrecte");
			}

			if ( names.size() >= 2 )
				this.frame.getControler().nouvellePartie(names);
		}
		else if ( e.getSource() == this.retourB )
			this.frame.openPage( new MainMenu(frame) );
		else if ( e.getSource() instanceof ChoixJoueursLine )
			((ChoixJoueursLine) e.getSource()).toggleTextField();
	}
}


