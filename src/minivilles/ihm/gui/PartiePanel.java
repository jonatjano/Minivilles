package minivilles.ihm.gui;

import minivilles.util.Utility;
import minivilles.metier.carte.Etablissement;
import minivilles.metier.*;
import minivilles.GestionJeu;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.plaf.synth.Region;


public class PartiePanel extends JPanel implements ActionListener, ItemListener
{
	private MainFrame 	frame;
	private JLabel		numTourL, joueurTourL, resLanceL;
	private JButton 	endTourB, quittGameB, rollDiceB;
	private JComboBox	nbDeCB;

	private GestionJeu 	gj;
	private int[] 		dernierLance;


	public PartiePanel (MainFrame frame, GestionJeu gj)
	{
		this.frame 	= frame;
		this.frame.getBgPanel().setLayout( new BorderLayout() );
		this.dernierLance = null;

		this.gj 	= gj;
		this.setLayout( new BorderLayout() );
		this.setVisible(true);


		/* Panel de gauche */

		JPanel leftP = new JPanel();
		leftP.setLayout( new BorderLayout() );


		/* Panel central */

		JPanel centerP = new JPanel();
		centerP.setLayout( new GridBagLayout() );

		int[] dim = Utility.getPercentOfFrame(this.frame, 75, 85);
		JCanvas canvas = new JCanvas( new Dimension(dim[0], dim[1]) );
		canvas.setBorder( BorderFactory.createLineBorder(Color.black) );
		canvas.setBackground(Color.RED);


		// Offset et rotation en fonction du nombre de joueurs
		double 	rot 	= Math.toRadians(360 / this.gj.getTabJoueur().length),
				offset 	= 150;

		// Coordonnées du centre du canvas
		double 	centerX 	= canvas.getPreferredSize().getWidth() / 2,
				centerY 	= canvas.getPreferredSize().getHeight() / 2;
		// Les coordonnées à incrémenter
		int 	x 		= (int) centerX,
				y 		= (int) (centerY + offset);



		
		int len 	= this.gj.getTabJoueur().length;
		int indexJ 	= this.gj.calcIndexCourant();

		BufferedImage imgCartes = null;
		try 					{ imgCartes = ImageIO.read( new File("../images/etablissements.png") ); }
		catch (IOException e)	{e.printStackTrace();}

		// Affichage des noms des joueurs
		int[][] coords = new int[len][2];
		int cpt = 0;
		for (int i = 0; i < len; i++)
		{
			coords[i] = Utility.rotateAround(centerX, centerY, x, y, rot);
			x = coords[i][0];
			y = coords[i][1];

			canvas.printString( this.gj.getTabJoueur()[ Utility.posModulo(indexJ - i - 1, len) ].getPrenom(), x, y );

			cpt++;
		}

		// Affichage des cartes du joueur
		int begX = 9,
			begY = 8;
		Dimension dimCard = new Dimension(185, 273);
		double rap = 0.5;
		double decalage = dimCard.getWidth()*rap*(1f/10);
		
		for (int i = 0; i < len; i++)
		{
			/* Affichage de la Main du joueur */
			ArrayList<Etablissement> main = this.gj.getTabJoueur()[ Utility.posModulo(indexJ - i - 1, len) ].getEtablissements();
			System.out.println( (main.size()/2f) );
			int[] coordCard = Utility.rotateAround( coords[i][0],
													coords[i][1],
													coords[i][0] - 100,//( (main.size() <= 4) ? (int) ((dimCard.getWidth()*rap + decalage)*(main.size()/4f)) : (int) ((dimCard.getWidth()*rap + decalage)*2f) ),
													coords[i][1] + (dimCard.getHeight()*rap)/2 + (dimCard.getHeight()*rap*(1f/10)),
													rot*(i+1) );
			
			int lig = 0;
			int col = 0;
			for (int j = 0; j < main.size(); j++)
			{
				
				canvas.printImage( imgCartes, new Dimension( 	(int) (dimCard.getWidth() * rap),
																(int) (dimCard.getHeight() * rap) ),
																(begX + main.get(j).getCol()*(185+5)),
																(begY + main.get(j).getLig()*(273+7)),
																dimCard,
																coordCard[0] + (int) (col * (dimCard.getWidth()*rap + decalage)),
																coordCard[1] + (int) (lig * (dimCard.getHeight()*rap + decalage)) );
				col++;
				if (col > 7)
				{
					lig++;
					col = 0;
				}
			}
				
		}
		centerP.add( canvas );
		leftP.add( centerP );


		/* Panel du haut */

		JPanel upP = new JPanel();
		upP.setLayout( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();

		this.numTourL 		= new JLabel( "Tour n°" + gj.getNumTour() );
		this.numTourL.setFont( this.frame.getFont() );
		upP.add( this.numTourL, c );

		this.joueurTourL	= new JLabel( "Tour de " + this.gj.getTabJoueur()[ indexJ ].getPrenom() );
		this.joueurTourL.setFont( this.frame.getFont() );
		c.gridy = 1;
		upP.add( this.joueurTourL, c );

		leftP.add( upP, BorderLayout.NORTH );



		/* Panel du bas */

		JPanel bottomP = new JPanel();

		this.quittGameB 	= new JButton("Quitter la partie");
		this.quittGameB.setFont( this.frame.getFont() );
		this.quittGameB.addActionListener( this );
		bottomP.add( this.quittGameB );

		leftP.add( bottomP, BorderLayout.SOUTH );

		this.add( leftP );


		/* Panel de droite */

		JPanel rightP = new JPanel();
		rightP.setLayout( new BoxLayout(rightP, BoxLayout.Y_AXIS) );

		JPanel rightTopP = new JPanel();

		/* Panel haut droite */
		// Création du tableau qu'affichera la combobx permettant de choisir le nombre de dés
		int nbDesJoueurs = this.gj.getJoueurActuel().getNbDes();
		String[] tabNbDes = new String[nbDesJoueurs];
		for ( int i = 0; i < this.gj.getJoueurActuel().getNbDes(); i++ )
			tabNbDes[i] = "" + (i+1);

		this.nbDeCB 	= new JComboBox( tabNbDes );
		this.nbDeCB.setFont( this.frame.getFont() );
		this.nbDeCB.setSelectedIndex(0);
		// this.nbDeCB.addItemListener( this );
		rightTopP.add( this.nbDeCB );

		// Création des boutons d'action du tour
		this.rollDiceB	= new JButton("Lancer les dés");
		this.rollDiceB.setFont( this.frame.getFont() );
		this.rollDiceB.addActionListener( this );
		rightTopP.add( this.rollDiceB );

		this.resLanceL 	= new JLabel("Résultat du lancé : ");
		this.resLanceL.setFont( this.frame.getFont() );
		rightTopP.add( this.resLanceL );

		this.endTourB 	= new JButton("Fin du tour");
		this.endTourB.setFont( this.frame.getFont() );
		this.endTourB.addActionListener( this );
		rightTopP.add( this.endTourB );

		rightP.add( rightTopP );


		/* Panel bas droite (ACHAT) */
		JPanel rightBottP = new JPanel();

		int[] dim2 = Utility.getPercentOfFrame(this.frame, 20, 40);
		JCanvas achatP = new JCanvas( new Dimension(dim2[0], dim2[1]) );
		achatP.setBorder( BorderFactory.createLineBorder(Color.black) );
		rightBottP.add( achatP );

		rightP.add( rightBottP );


		// this.add( rightP, BorderLayout.EAST );
		this.add( rightP, BorderLayout.EAST );


		this.setVisible(true);
	}

	public void majDisplay (GestionJeu gj)
	{
		int indexJ = this.gj.calcIndexCourant();

		this.numTourL.setText( "Tour n°" + gj.getNumTour() );
		this.joueurTourL.setText( "Tour de " + this.gj.getTabJoueur()[ indexJ ].getPrenom() );
	}

	public void actionPerformed (ActionEvent e)
	{
		if 	( e.getSource() == this.quittGameB )
			this.frame.openPage( new MainMenu(frame) );
		else if ( e.getSource() == this.rollDiceB )
		{
			this.dernierLance = this.frame.getControler().lancerDe( Integer.parseInt( (String) this.nbDeCB.getSelectedItem() ) );
			this.resLanceL.setText( "Résultat du lancé : " + ((dernierLance.length == 1) ? ("" + dernierLance[0]) : String.format("%d (%d + %d)", (dernierLance[0] + dernierLance[1]), dernierLance[0], dernierLance[1])) );
			// Voir si a double
			// this.frame.getControler().getIhm().lancerDe( this.nbDeCB.getSelectedIndex() );
		}
		else if ( e.getSource() == this.endTourB )
		{
			if (this.dernierLance != null)
			{
				this.frame.getControler().reponseTourJoueur( this.dernierLance );
				this.dernierLance = null;
				this.resLanceL.setText( "Résultat du lancé : " );

				// Rend disponible l'achat d'établissement / la construction de monument
			}
			else
				System.out.println("AFFICHER UN MESSAGE");
		}
	}

	public void itemStateChanged (ItemEvent e)
	{

	}
}


