package minivilles.ihm.gui;

import minivilles.util.Utility;
import minivilles.metier.*;
import minivilles.GestionJeu;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.plaf.synth.Region;


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


		/* Panel de droite */

		JPanel rightP = new JPanel();
		rightP.setLayout( new BorderLayout() );


		/* Panel central */

		JCanvas centerP = new JCanvas(new Dimension(800, 600));
		centerP.setBackground(Color.RED);


		// Offset et rotation en fonction du nombre de joueurs
		double 	rot 	= Math.toRadians(360 / this.gj.getTabJoueur().length),
				offset 	= 150;

		// Coordonnées du centre du canvas
		double 	centerX 	= centerP.getPreferredSize().getWidth() / 2,
				centerY 	= centerP.getPreferredSize().getHeight() / 2;
		// Les coordonnées à incrémenter
		int 	x 		= (int) centerX,
				y 		= (int) (centerY + offset);



		
		int len = this.gj.getTabJoueur().length;
		int indexJ = this.gj.calcIndexCourant();

		// Affichage des noms des joueurs
		int[][] coords = new int[len][2];
		int cpt = 0;
		for (int i = 0; i < len; i++)
		{
			coords[i] = Utility.rotateAround(centerX, centerY, x, y, rot);
			x = coords[i][0];
			y = coords[i][1];

			centerP.printString( this.gj.getTabJoueur()[ Utility.posModulo(indexJ - i - 1, len) ].getPrenom(), x, y );

			cpt++;
		}

		// Affichage des cartes du joueur
		for (int i = 0; i < len; i++)
		{
			int[] coordCard = Utility.rotateAround( coords[i][0], coords[i][1], coords[i][0], coords[i][1] + 80, rot*(i+1) );

			/* Main du joueur */
			try 
			{
			    centerP.printImage( ImageIO.read(new File("../images/image.jpg")), new Dimension(50, 70), coordCard[0], coordCard[1] );
			} 
			catch (IOException e) 
			{
			    e.printStackTrace();
			}
		}

		rightP.add( centerP );
		


		/* Panel du haut */

		JPanel upP = new JPanel();

		this.numTourL 		= new JLabel( "Tour n°" + gj.getNumTour() );
		upP.add( this.numTourL );
		this.joueurTourL	= new JLabel( "Tour de " + this.gj.getTabJoueur()[ indexJ ].getPrenom() );
		upP.add( this.joueurTourL );

		rightP.add( upP, BorderLayout.NORTH );


		/* Panel de gauche */

		JPanel leftP = new JPanel();
		// leftP.setPreferredSize(new Dimension(500, 100));

		this.endTourB 		= new JButton("Fin du tour");
		this.endTourB.addActionListener( this );
		leftP.add( this.endTourB );

		// this.add( leftP, BorderLayout.EAST );
		this.add( leftP, BorderLayout.EAST );


		/* Panel du bas */

		JPanel bottomP = new JPanel();

		this.quittGameB 	= new JButton("Quitter la partie");
		this.quittGameB.addActionListener( this );
		bottomP.add( this.quittGameB );

		rightP.add( bottomP, BorderLayout.SOUTH );


		this.add( rightP );


		this.setVisible(true);
	}

	public void majDisplay (GestionJeu gj)
	{
		int indexJ = this.gj.calcIndexCourant();

		this.numTourL.setText( "Tour n°" + gj.getNumTour() );
		this.joueurTourL.setText( "Tour de" + this.gj.getTabJoueur()[ indexJ ].getPrenom() );
	}

	public void actionPerformed (ActionEvent e)
	{
		if ( e.getSource() == this.quittGameB )
			this.frame.openPage( new MainMenu(frame) );
		else if ( e.getSource() == this.endTourB )
			this.frame.getControler().reponseTourJoueur(new int[]{6});
	}
}


