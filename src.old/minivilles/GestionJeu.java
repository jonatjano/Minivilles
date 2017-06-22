package minivilles;

import minivilles.ihm.Ihm;
import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;
import java.util.Scanner;
import java.util.ArrayList;


public class GestionJeu
{
	private static final int BEG_MON = 666;

	private Ihm 		ihm;
	private Pioche 		pioche;
	private int 		nbJoueur,
						nbBoucle,
						indexFirstPlayer,
						numTour;
	private Joueur[] 	tabJoueur;
	private int 		banque;
	private Joueur 		joueurGagnant;
	private Joueur 		joueurActuel;


	public GestionJeu (Ihm ihm, ArrayList<String> names)
	{
		this.ihm 				= ihm;
		this.pioche 			= new Pioche();
		this.nbJoueur 			= names.size();
		this.indexFirstPlayer	= -1;

		this.tabJoueur = new Joueur[this.nbJoueur];
		for (int i = 0; i < nbJoueur; i++)
			tabJoueur[i] = new Joueur(names.get(i), GestionJeu.BEG_MON);

		this.joueurActuel 	= null;
		this.joueurGagnant	= null;
	}

	public void lancer ()
	{
		this.indexFirstPlayer 	= 0;
		this.joueurActuel		= this.tabJoueur[ this.indexFirstPlayer ];
		this.nbBoucle 			= this.indexFirstPlayer;
		this.numTour			= 1;

		this.ihm.displayDebutPartie( this );
		this.ihm.displayTourJoueur( this );
	}

	public void resultatTour (int[] valDe)
	{
		boolean aUnDouble 	= false;

		if ( !aUnDouble )
			this.nbBoucle++;
		this.nbBoucle = this.nbBoucle%nbJoueur;

		// Nouveau tour
		if (this.nbBoucle == indexFirstPlayer && !aUnDouble)
			this.numTour++;

		this.joueurActuel = this.tabJoueur[ this.nbBoucle ];

		aUnDouble = valDe.length == 2 && this.joueurActuel.hasParc() && valDe[0] == valDe[1];


		// Vérifie si un joueur a gagné
		for (Joueur j : tabJoueur)
			if ( j.aGagne() )
				this.joueurGagnant = j;

		if ( this.joueurGagnant == null)
			this.ihm.displayTourJoueur( this );
		else
			// Affiche la cérémonie du grand vainqueur
			this.ihm.displayFinPartie(this.joueurGagnant, this.numTour);
	}


	public Pioche 	getPioche ()			{ return this.pioche; 			}
	public int 		getIndexFirstPlayer ()	{ return this.indexFirstPlayer; }
	public int 		getNumTour ()			{ return this.numTour; 			}
	public Joueur[] getTabJoueur ()			{ return this.tabJoueur; 		}
	public Joueur 	getJoueurActuel ()		{ return this.joueurActuel; 	}
}
