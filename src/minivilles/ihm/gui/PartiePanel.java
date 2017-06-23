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
	private JButton 	endTourB, quittGameB, rollDiceB, buyB;
	private JTextField	buyTF;
	private JComboBox	nbDeCB;
	private JPanel		centerP, topP, bottomP, leftP, rightTopP, rightCenterP, rightBottP, rightP;
	private JCanvas 	canvas, achatP;

	private Dimension		dimCard;
	private double 			rapCard;
	private BufferedImage 	imgCartes;
	private GestionJeu 		gj;
	private int[] 			dernierLance;


	public PartiePanel (MainFrame frame, GestionJeu gj)
	{
		this.frame 	= frame;
		this.frame.getBgPanel().setLayout( new BorderLayout() );
		this.dernierLance = null;

		this.gj 	= gj;
		this.setLayout( new BorderLayout() );
		this.setVisible(true);

		/* Variables utiles */

		this.imgCartes = null;
		try 					{ this.imgCartes = ImageIO.read( new File("../images/etablissements.png") ); }
		catch (IOException e)	{e.printStackTrace();}
		this.dimCard = new Dimension(185, 273);
		this.rapCard = 0.5;


		/* Panel de gauche */

		leftP = new JPanel();
		leftP.setLayout( new BorderLayout() );


		/* Panel central */

		centerP = new JPanel();
		centerP.setLayout( new GridBagLayout() );

		int[] dim = Utility.getPercentOfFrame(this.frame, 75, 85);
		this.canvas = new JCanvas( new Dimension(dim[0], dim[1]) );
		this.canvas.setBorder( BorderFactory.createLineBorder(Color.black) );

		centerP.add( this.canvas );
		leftP.add( centerP );	


		/* Panel du haut */

		topP = new JPanel();
		topP.setLayout( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();

		this.numTourL 		= new JLabel( "Tour n°" + gj.getNumTour() );
		this.numTourL.setFont( this.frame.getFont() );
		topP.add( this.numTourL, c );

		this.joueurTourL	= new JLabel( "Tour de " + this.gj.getTabJoueur()[ this.gj.calcIndexCourant() ].getPrenom() );
		this.joueurTourL.setFont( this.frame.getFont() );
		c.gridy = 1;
		topP.add( this.joueurTourL, c );

		leftP.add( topP, BorderLayout.NORTH );



		/* Panel du bas */

		bottomP = new JPanel();

		this.quittGameB 	= new JButton("Quitter la partie");
		this.quittGameB.setFont( this.frame.getFont() );
		this.quittGameB.addActionListener( this );
		bottomP.add( this.quittGameB );

		leftP.add( bottomP, BorderLayout.SOUTH );

		this.add( leftP );


		/* Panel de droite */

		rightP = new JPanel();
		rightP.setLayout( new BoxLayout(rightP, BoxLayout.Y_AXIS) );

		
		/* Panel haut droite */
		// Création du tableau qu'affichera la combobx permettant de choisir le nombre de dés
		this.rightTopP = new JPanel();
		this.rightTopP.setLayout( new GridLayout(2, 2) );

		int nbDesJoueurs = this.gj.getJoueurActuel().getNbDes();
		String[] tabNbDes = new String[nbDesJoueurs];
		for ( int i = 0; i < this.gj.getJoueurActuel().getNbDes(); i++ )
			tabNbDes[i] = "" + (i+1);

		this.nbDeCB 	= new JComboBox( tabNbDes );
		this.nbDeCB.setFont( this.frame.getFont() );
		this.nbDeCB.setSelectedIndex(0);
		// this.nbDeCB.addItemListener( this );
		this.rightTopP.add( this.nbDeCB );

		// Création des boutons d'action du tour
		this.rollDiceB	= new JButton("Lancer les dés");
		this.rollDiceB.setFont( this.frame.getFont() );
		this.rollDiceB.addActionListener( this );
		this.rightTopP.add( this.rollDiceB );

		this.resLanceL 	= new JLabel("Résultat du lancé : ");
		this.resLanceL.setFont( this.frame.getFont() );
		this.rightTopP.add( this.resLanceL );

		this.endTourB 	= new JButton("Fin du tour");
		this.endTourB.setFont( this.frame.getFont() );
		this.endTourB.addActionListener( this );
		this.endTourB.setEnabled(false);
		this.rightTopP.add( this.endTourB );

		this.rightP.add( this.rightTopP );


		/* Panel milieu droit */
		this.rightCenterP = new JPanel();
		this.rightCenterP.setLayout( new GridLayout( 1, 2) );//rightCenterP, BoxLayout.X_AXIS) );

		this.buyTF = new JTextField();
		this.rightCenterP.add( this.buyTF );

		this.buyB = new JButton("Acheter");
		this.buyB.addActionListener( this );
		this.buyB.setEnabled(false);
		this.rightCenterP.add( this.buyB );

		this.rightP.add( this.rightCenterP );


		/* Panel bas droite (ACHAT) */
		this.rightBottP = new JPanel();

		int[] dim2 = Utility.getPercentOfFrame(this.frame, 20, 75);
		this.achatP = new JCanvas( new Dimension(dim2[0], dim2[1]) );
		this.achatP.setBorder( BorderFactory.createLineBorder(Color.black) );
		this.achatP.setEnabled(false);
		this.rightBottP.add( this.achatP );

		this.rightP.add( this.rightBottP );


		// this.add( rightP, BorderLayout.EAST );
		this.add( rightP, BorderLayout.EAST );


		this.setVisible(true);
	}

	public void majDisplay (GestionJeu gj)
	{
		int indexJ 	= this.gj.calcIndexCourant();
		int len 	= this.gj.getTabJoueur().length;

		this.numTourL.setText( "Tour n°" + gj.getNumTour() );
		this.joueurTourL.setText( "Tour de " + this.gj.getTabJoueur()[ indexJ ].getPrenom() );


		this.canvas.clearAll();
		this.achatP.clearAll();

		// Offset et rotation en fonction du nombre de joueurs
		double 	rot 	= Math.toRadians(360 / this.gj.getTabJoueur().length),
				offset 	= 150;

		int begX = 9,
			begY = 8;

		// Coordonnées du centre du this.canvas
		double 	centerX 	= this.canvas.getPreferredSize().getWidth() / 2,
				centerY 	= this.canvas.getPreferredSize().getHeight() / 2;
		// Les coordonnées à incrémenter
		int 	x 		= (int) centerX,
				y 		= (int) (centerY + offset);

		// Affichage des noms des joueurs
		int[][] coords = new int[len][2];
		int cpt = 0;
		for (int i = 0; i < len; i++)
		{
			coords[i] = Utility.rotateAround(centerX, centerY, x, y, rot);
			x = coords[i][0];
			y = coords[i][1];

			Joueur j = this.gj.getTabJoueur()[ Utility.posModulo(indexJ - i - 1, len) ];
			this.canvas.printString( String.format("%-20s (%2d Pièces)", j.getPrenom(), j.getMonnaie()), x, y );

			cpt++;
		}

		// Affichage des cartes des joueurs
		double decalage = this.dimCard.getWidth()*this.rapCard*(1f/10);

		for (int i = 0; i < len; i++)
		{
			/* Affichage de la Main du joueur */
			ArrayList<Etablissement> main = this.gj.getTabJoueur()[ Utility.posModulo(indexJ - i - 1, len) ].getEtablissements();
			int[] coordCard = Utility.rotateAround( coords[i][0],
													coords[i][1],
													coords[i][0],//( (main.size() <= 4) ? (int) ((this.dimCard.getWidth()*this.rapCard + decalage)*(main.size()/4f)) : (int) ((this.dimCard.getWidth()*this.rapCard + decalage)*2f) ),
													coords[i][1] + (this.dimCard.getHeight()*this.rapCard)/2 + (this.dimCard.getHeight()*this.rapCard*(3f/10)),
													rot*(i+1) );
			
			int lig = 0;
			int col = 0;
			for (int j = 0; j < main.size(); j++)
			{
				
				this.canvas.printImage( 	imgCartes,
									new Dimension( (int) (this.dimCard.getWidth() * this.rapCard), (int) (this.dimCard.getHeight() * this.rapCard) ),
									(begX + main.get(j).getCol()*(185+5)),
									(begY + main.get(j).getLig()*(273+7)),
									this.dimCard,
									coordCard[0] + (int) (col * (this.dimCard.getWidth()*this.rapCard + decalage)),
									coordCard[1] + (int) (lig * (this.dimCard.getHeight()*this.rapCard + decalage)) );
				col++;
				if (col > 7)
				{
					lig++;
					col = 0;
				}
			}	
		}

		// Affichage des achats
		Pioche pioche = this.gj.getPioche();
		int ligSrc = 0,
			colSrc = 0,
			lig = 0,
			col = 0;

		x = 45 + (int) (this.dimCard.getWidth()*this.rapCard/2f);
		y = 30 + (int) (this.dimCard.getHeight()*this.rapCard/2f);

		int	centerCardX = 0,
			centerCardY = 0;

		for (int j = 0; j < 15; j++)
		{
			centerCardX = x + (int) (col*(185+5)*this.rapCard);
			centerCardY = y + (int) (lig*(273+42)*this.rapCard);

			this.achatP.printString(	String.format("%2d  x%d", (j+1), pioche.getNbCartes()[j] ),
										(int) (centerCardX - this.dimCard.getWidth()*this.rapCard/4f),
										(int) (centerCardY - this.dimCard.getWidth()*this.rapCard/2f - 25) );
			this.achatP.printImage( 	imgCartes,
								new Dimension( (int) (this.dimCard.getWidth() * this.rapCard),(int) (this.dimCard.getHeight() * this.rapCard) ),
								(begX + colSrc*(185+5)),
								(begY + ligSrc*(273+7)),
								this.dimCard,
								centerCardX,
								centerCardY 	);
			col++;
			if (col >= 3)
			{
				lig++;
				col = 0;
			}

			colSrc++;
			if (colSrc >= 5)
			{
				ligSrc++;
				colSrc = 0;
			}
		}
	}

	public void actionPerformed (ActionEvent e)
	{
		if 	( e.getSource() == this.quittGameB )
			this.frame.openPage( new MainMenu(frame) );
		else if ( e.getSource() == this.rollDiceB )
		{
			if ( !this.endTourB.isEnabled() )
			{
				this.dernierLance = this.frame.getControler().lancerDe( Integer.parseInt( (String) this.nbDeCB.getSelectedItem() ) );
				this.resLanceL.setText( "Résultat du lancé : " + ((dernierLance.length == 1) ? ("" + dernierLance[0]) : String.format("%d (%d + %d)", (dernierLance[0] + dernierLance[1]), dernierLance[0], dernierLance[1])) );
				this.endTourB.setEnabled(true);
				this.buyB.setEnabled(true);
				this.rollDiceB.setEnabled(false);
				// Voir si a double
				// this.frame.getControler().getIhm().lancerDe( this.nbDeCB.getSelectedIndex() );
			}
		}
		else if ( e.getSource() == this.endTourB )
		{
			if (this.dernierLance != null)
			{
				this.frame.getControler().reponseTourJoueur( this.dernierLance );
				this.dernierLance = null;
				this.resLanceL.setText( "Résultat du lancé : " );
				this.endTourB.setEnabled(false);
				this.buyB.setEnabled(false);
				this.rollDiceB.setEnabled(true);
				this.buyTF.setText("");
			}
			else
				System.out.println("AFFICHER UN MESSAGE");
		}
		else if ( e.getSource() == this.buyB )
		{
			if ( this.buyTF.getText().matches("[0-9]+") )
			{
				Joueur 			j 	= this.gj.getJoueurActuel();
				Etablissement 	et 	= this.gj.getPioche().achatEtablissement( Integer.parseInt(this.buyTF.getText()) - 1, j);
				if ( et != null )
				{
					j.addEtablissement( et );
					e.setSource( this.endTourB );
					this.actionPerformed( e );
				}
			}
		}
	}

	public void itemStateChanged (ItemEvent e)
	{

	}
}


