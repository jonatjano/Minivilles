package minivilles.ihm.gui;

import minivilles.util.Utility;
import minivilles.metier.carte.Etablissement;
import minivilles.metier.carte.Monument;
import minivilles.metier.*;
import minivilles.GestionJeu;
import minivilles.Controleur;
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
	private JLabel		numTourL, joueurTourL, resLanceL, achatL;
	private JButton 	endTourB, quittGameB, rollDiceB, buyB;
	private JTextField	buyTF;
	private JComboBox	nbDeCB;
	private JPanel		centerP, topP, bottomP, leftP, rightTopP, rightCenterP, rightBottP, rightP;
	private JCanvas 	canvas, achatP;

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
<<<<<<< HEAD
		try 					{ this.imgCartes = ImageIO.read( new File("../images/etablissements.png") ); }
		catch (IOException e)	{ e.printStackTrace(); }
		this.rapCard = this.frame.getWidth() * 0.00025;
=======
		try 					{ this.imgCartes = ImageIO.read( new File(Controleur.PATH + "/images/etablissements.png") ); }
		catch (IOException e)	{e.printStackTrace();}
		System.out.println(1500/3920f);
		this.rapCard = this.frame.getWidth() / 3750f;//0.5;
>>>>>>> ec4ca484d66201f5a849d0a9392feba6d6858052


		/* Panel de gauche */

		leftP = new JPanel();
		leftP.setLayout( new BorderLayout() );


		/* Panel central */

		centerP = new JPanel();
		centerP.setLayout( new GridBagLayout() );

		int[] dim = Utility.getPercentOfFrame(this.frame, 65, 80);
		this.canvas = new JCanvas( this.frame, new Dimension(dim[0], dim[1]) );
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
		this.rightCenterP.setLayout( new GridLayout( 2, 1) );


		JPanel topRightCenterP = new JPanel();
		this.achatL = new JLabel("Construire un bâtiment (après le lancer de dé)");
		this.achatL.setFont( this.frame.getFont() );
		topRightCenterP.add( this.achatL );

		this.rightCenterP.add( topRightCenterP );


		JPanel bottRightCenterP = new JPanel();
		bottRightCenterP.setLayout( new GridLayout( 1, 2) );

		this.buyTF = new JTextField();
		this.buyTF.setEnabled(false);
		bottRightCenterP.add( this.buyTF );

		this.buyB = new JButton("Acheter");
		this.buyB.setFont( this.frame.getFont() );
		this.buyB.addActionListener( this );
		this.buyB.setEnabled(false);
		bottRightCenterP.add( this.buyB );

		this.rightCenterP.add( bottRightCenterP );
		this.rightP.add( this.rightCenterP );


		/* Panel bas droite (ACHAT) */
		this.rightBottP = new JPanel();

		int[] dim2 = Utility.getPercentOfFrame(this.frame, 20, 75);
		this.achatP = new JCanvas( this.frame, new Dimension(dim2[0], dim2[1]) );
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

		// Affichage les cartes des joueurs
		double decalage = Etablissement.DIM_ET.getWidth()*this.rapCard*(1f/10);

		for (int i = 0; i < len; i++)
		{
			/* Affichage de la Main du joueur */
			ArrayList<Etablissement> main = this.gj.getTabJoueur()[ Utility.posModulo(indexJ - i - 1, len) ].getEtablissements();
			int[] coordCard = Utility.rotateAround( coords[i][0],
													coords[i][1],
													coords[i][0],//( (main.size() <= 4) ? (int) ((Etablissement.DIM_ET.getWidth()*this.rapCard + decalage)*(main.size()/4f)) : (int) ((Etablissement.DIM_ET.getWidth()*this.rapCard + decalage)*2f) ),
													coords[i][1] + (Etablissement.DIM_ET.getHeight()*this.rapCard)/2 + (Etablissement.DIM_ET.getHeight()*this.rapCard*(3f/10)),
													rot*(i+1) );
			
			int lig = 0;
			int col = 0;
			for (int j = 0; j < main.size(); j++)
			{
				
				this.canvas.printImage( 	imgCartes,
									new Dimension( (int) (Etablissement.DIM_ET.getWidth() * this.rapCard), (int) (Etablissement.DIM_ET.getHeight() * this.rapCard) ),
									(begX + (int) (main.get(j).getCol()*(Etablissement.DIM_ET.getWidth()+5))),
									(begY + (int) (main.get(j).getLig()*(Etablissement.DIM_ET.getHeight()+7))),
									Etablissement.DIM_ET,
									coordCard[0] + (int) (col * (Etablissement.DIM_ET.getWidth()*this.rapCard + decalage)),
									coordCard[1] + (int) (lig * (Etablissement.DIM_ET.getHeight()*this.rapCard + decalage)) );
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

		x = (int) (110*this.rapCard + Etablissement.DIM_ET.getWidth()*this.rapCard/2f);
		y = (int) (90*this.rapCard + Etablissement.DIM_ET.getHeight()*this.rapCard/2f);

		int	centerCardX = 0,
			centerCardY = 0;

		// Affichage établissements
		for (int j = 0; j < 15; j++)
		{
			if (pioche.getNbCartes()[j] > 0)
			{
				centerCardX = x + (int) (col*(185+5)*this.rapCard);
				centerCardY = y + (int) (lig*(273+42)*this.rapCard);

				this.achatP.printString(	String.format("%2d  x%d", (j+1), pioche.getNbCartes()[j] ),
											(int) (centerCardX - Etablissement.DIM_ET.getWidth()*this.rapCard/4f),
											(int) (centerCardY - Etablissement.DIM_ET.getWidth()*this.rapCard/2f - 55*this.rapCard) );
				this.achatP.printImage( 	imgCartes,
											new Dimension( (int) (Etablissement.DIM_ET.getWidth() * this.rapCard),(int) (Etablissement.DIM_ET.getHeight() * this.rapCard) ),
											(int) (begX + colSrc*(185+5)),
											(int) (begY +  ligSrc*(273+7)),
											Etablissement.DIM_ET,
											centerCardX,
											centerCardY 	);
				col++;
				if (col >= 3)
				{
					lig++;
					col = 0;
				}
			}
			
			colSrc++;
			if (colSrc >= 5)
			{
				ligSrc++;
				colSrc = 0;
			}
		}

		begX = 7;
		begY = 4;
			// 10
		// Affichage monuments
		// ArrayList<Monument> tabMon = this.gj.getJoueurActuel().getMonuments();
		// for (Monument mon : tabMon)
		// {
		// 	if ( !mon.estConstruit() )
		// 	{
		// 		this.achatP.printImage( 	imgCartes,
		// 									new Dimension( (int) (Etablissement.DIM_ET.getWidth() * this.rapCard),(int) (Etablissement.DIM_ET.getHeight() * this.rapCard) ),
		// 									(int) (begX + colSrc*(Monument.DIM_MON.getWidth()+10)),
		// 									(int) (begY + ligSrc*(Monument.DIM_MON.getHeight()+10)),
		// 									Etablissement.DIM_ET,
		// 									centerCardX,
		// 									centerCardY 	);
		// 	}
		// }
	}

	public void actionPerformed (ActionEvent e)
	{
		/* PRESSION BOUTON - FIN PARTIE */
		if 	( e.getSource() == this.quittGameB )
		{
			this.frame.openPage( new MainMenu(frame) );
		}
		/* PRESSION BOUTON - LANCE DE DE(S) */
		else if ( e.getSource() == this.rollDiceB )
		{
			if ( !this.endTourB.isEnabled() )
			{
				this.dernierLance = this.frame.getControler().lancerDe( Integer.parseInt( (String) this.nbDeCB.getSelectedItem() ) );
				this.resLanceL.setText( "Résultat du lancé : " + ((dernierLance.length == 1) ? ("" + dernierLance[0]) : String.format("%d (%d + %d)", (dernierLance[0] + dernierLance[1]), dernierLance[0], dernierLance[1])) );
				
				// Si le joueur a fait un double et a le parc d'attraction...
				if ( this.gj.hasPlayerDouble(this.dernierLance) )
				{
					int ans = 0;
					ans = JOptionPane.showConfirmDialog (this.frame, "Votre lancé de dé est de '%2d'\nVoulez-vous relancer ?", "Relance", ans);
					if (ans == JOptionPane.YES_OPTION)
					{
						this.dernierLance = this.frame.getControler().lancerDe( Integer.parseInt( (String) this.nbDeCB.getSelectedItem() ) );
						this.resLanceL.setText( "Résultat du lancé : " + ((dernierLance.length == 1) ? ("" + dernierLance[0]) : String.format("%d (%d + %d)", (dernierLance[0] + dernierLance[1]), dernierLance[0], dernierLance[1])) );
					}
				}

				this.frame.getControler().activateCardsAction( Utility.arraySum(this.dernierLance) );
				this.majDisplay( this.gj );
				this.endTourB.setEnabled(true);
				this.buyB.setEnabled(true);
				this.buyTF.setEnabled(true);
				this.rollDiceB.setEnabled(false);
			}
		}
		/* PRESSION BOUTON - FIN TOUR */
		else if ( e.getSource() == this.endTourB )
		{
			if (this.dernierLance != null)
			{
				this.frame.getControler().reponseTourJoueur( this.dernierLance );
				this.dernierLance = null;
				this.resLanceL.setText( "Résultat du lancé : " );

				this.endTourB.setEnabled(false);
				this.buyB.setEnabled(false);
				this.buyTF.setEnabled(false);
				this.buyTF.setText("");
				this.rollDiceB.setEnabled(true);
			}
			else
				System.out.println("AFFICHER UN MESSAGE");
		}
		else if ( e.getSource() == this.buyB )
		{
			if ( this.buyTF.getText().matches("[0-9]+") )
			{
				try
				{
					Joueur 			j 	= this.gj.getJoueurActuel();
					Etablissement 	et 	= this.gj.getPioche().achatEtablissement( Integer.parseInt(this.buyTF.getText()) - 1, j);
					if ( et != null )
					{
						j.addEtablissement( et );
						e.setSource( this.endTourB );
						this.actionPerformed( e );
					}
					else
						this.frame.errorMessage("Erreur", "Impossible d'acheter : vous n'avez plus assez d'argent !", JOptionPane.ERROR_MESSAGE);
				}
				catch (IndexOutOfBoundsException ex)
				{
					this.frame.errorMessage("Erreur", "Impossible d'acheter : index incorrect ou indisponible !",  JOptionPane.ERROR_MESSAGE);
				}
			}
			else
				this.frame.errorMessage("Erreur", "Impossible d'acheter : entrez un chiffre !",  JOptionPane.ERROR_MESSAGE);
		}
	}

	public void itemStateChanged (ItemEvent e)
	{

	}
}


