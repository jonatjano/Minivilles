package minivilles.ihm.gui;

import minivilles.reseau.*;
import java.io.IOException;
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
		this.frame.getBgPanel().setLayout( new GridBagLayout() );
		try 					{ this.frame.getBgPanel().setImage(null); }
		catch (IOException e) 	{e.printStackTrace();}
		

		int maxJ = Server.MAX_J;
		this.setLayout( new GridLayout(maxJ + 2, 1) );

		this.lines		= new ChoixJoueursLine[ maxJ ];

		for (int i = 0; i < maxJ; i++)
		{
			this.lines[i] 	= new ChoixJoueursLine(this, (i+1));
			this.add( this.lines[i] );
		}

		// // Ajout des évènements à la pression de la touche "Tab"
		// for (int i = 0; i < maxJ; i++)
		// {
		// 	if (i != maxJ -1)
		// 	{
		// 		this.lines[i].addKeyListener(new KeyAdapter() {
		// 			private ChoixJoueursLine line;

		// 			@Override
		// 			public void keyPressed (KeyEvent e)
		// 			{
		// 				if( e.getKeyCode() == KeyEvent.VK_TAB )
		// 					line.toggleTextField();
		// 			}

		// 			private KeyAdapter init(ChoixJoueursLine line)
		// 			{
		// 		        this.line = line;
		// 		        return this;
		// 		    }
		// 		}.init(this.lines[i]) );
		// 		this.lines[i].setFocusTraversalKeysEnabled(false);
		// 	}
		// }


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
			boolean isUnique 	= true;
			boolean isTooLong 	= false;
			for (ChoixJoueursLine line : lines)
			{
				if ( line.getText().matches(".*[a-zA-Z].*") )
				{
					for (ChoixJoueursLine otherLine : lines)
					{
						if 		( line.getText().length() > 10 )
							isTooLong = true;
						else if ( otherLine != line && otherLine.getText().equals( line.getText() ) )
							isUnique = false;
					}
					if (isUnique && !isTooLong)
						names.add( line.getText() );
				}
			}

			
			if 		(isTooLong)
				this.frame.errorMessage("Impossible de lancer", "Un des noms est trop long\n(max. 10 caractères)", JOptionPane.ERROR_MESSAGE);
			else if (!isUnique)
				this.frame.errorMessage("Impossible de lancer", "Deux joueurs possèdent le même nom", JOptionPane.ERROR_MESSAGE);
			else if ( names.size() >= 2 )
				this.frame.getControler().nouvellePartie(names);
			else
				this.frame.errorMessage("Impossible de lancer", "Pas assez de joueurs", JOptionPane.ERROR_MESSAGE);
		}
		else if ( e.getSource() == this.retourB )
			this.frame.openPage( new MainMenu(frame) );
		else if ( e.getSource() instanceof ChoixJoueursLine )
			((ChoixJoueursLine) e.getSource()).toggleTextField();
	}
}


