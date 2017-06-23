package minivilles.ihm.gui;

import minivilles.util.Utility;
import minivilles.metier.*;
import minivilles.GestionJeu;
import javax.swing.plaf.synth.Region;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;


public class PartiePanel extends JPanel implements ActionListener
{
	private MainFrame 	frame;
	private JLabel		numTourL, joueurTourL;
	private JButton 	endTourB, quittGameB;

	private GestionJeu 	gj;


	public PartiePanel (MainFrame frame, GestionJeu gj)
	{
		this.frame 	= frame;
		this.gj 	= gj;
		this.setLayout( new BorderLayout() );
		this.setVisible(true);


		/* 

		/* Panel central */

		JCanvas centerP = new JCanvas(new Dimension(700, 500));
		centerP.setBackground(Color.RED);


		// Offset et rotation en fonction du nombre de joueurs
		double 	rot 	= Math.toRadians(360 / this.gj.getTabJoueur().length),
				offset 	= 200;

		// Coordonnées du centre du canvas
		double 	centerX 	= centerP.getPreferredSize().getWidth() / 2,
				centerY 	= centerP.getPreferredSize().getHeight() / 2;
		// Les coordonnées à incrémenter
		int 	x 		= (int) centerX,
				y 		= (int) (centerY + offset);


		int len = this.gj.getTabJoueur().length;
		int cpt = 0;
		for (int i = 0; i < len; i++)
		{

			double x1 = x - centerX;
			double y1 = y - centerY;

			double x2 = x1 * Math.cos(rot) - y1 * Math.sin(rot);
			double y2 = x1 * Math.sin(rot) + y1 * Math.cos(rot);

			x = (int) (x2 + centerX);
			y = (int) (y2 + centerY);


			centerP.printString( this.gj.getTabJoueur()[ Utility.posModulo(this.gj.getIndexFirstPlayer() - i - 1, len) ].getPrenom(), x, y );
			cpt++;
		}
		this.add( centerP );


		/* Panel du haut */

		JPanel upP = new JPanel();

		this.numTourL 		= new JLabel( "Tour n°" + gj.getNumTour() );
		upP.add( this.numTourL );
		this.joueurTourL	= new JLabel( "Tour de " + this.gj.getTabJoueur()[ this.gj.getIndexFirstPlayer() ].getPrenom() );
		upP.add( this.joueurTourL );

		this.add( upP, BorderLayout.NORTH );


		/* Panel de gauche */

		JPanel leftP = new JPanel();

		this.endTourB 		= new JButton("Fin du tour");
		this.endTourB.addActionListener( this );
		leftP.add( this.endTourB );

		this.add( leftP, BorderLayout.EAST );


		/* Panel du bas */

		JPanel bottomP = new JPanel();

		this.quittGameB 	= new JButton("Quitter la partie");
		this.quittGameB.addActionListener( this );
		bottomP.add( this.quittGameB );

		this.add( bottomP, BorderLayout.SOUTH );


		this.setVisible(true);
	}

	public void actionPerformed (ActionEvent e)
	{
		if ( e.getSource() == this.quittGameB )
			this.frame.openPage( new MainMenu(frame) );
		else if ( e.getSource() == this.endTourB )
			this.frame.getControler().getIhm().displayTourJoueur( this.gj );
	}
}


