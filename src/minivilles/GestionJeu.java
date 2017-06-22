package minivilles;

import minivilles.ihm.Ihm;
import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;
import java.util.Scanner;
import java.util.ArrayList;


public class GestionJeu
{
	private Ihm 		ihm;
	private Pioche 		pioche;
	private int 		nbJoueur,
						nbBoucle,
						indexFirstPlayer;
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
			tabJoueur[i] = new Joueur(names.get(i), 666);	// 3

		this.joueurActuel 	= null;
		this.joueurGagnant	= null;
	}

	public void lancer ()
	{
		this.indexFirstPlayer 	= 0;
		this.joueurActuel		= this.tabJoueur[ this.indexFirstPlayer ];
		this.nbBoucle 			= this.indexFirstPlayer;

		this.ihm.displayDebutPartie( this.tabJoueur );
		this.ihm.displayTourJoueur( 1, this.indexFirstPlayer, this.pioche, this.tabJoueur, this.joueurActuel );
	}

	public void resultatTour (int cptTour, int[] valDe)
	{
		boolean aUnDouble 	= false;

		if ( !aUnDouble )
			this.nbBoucle++;
		this.nbBoucle = this.nbBoucle%nbJoueur;

		// Nouveau tour
		if (this.nbBoucle == indexFirstPlayer && !aUnDouble)
			cptTour++;

		this.joueurActuel = this.tabJoueur[ this.nbBoucle ];

		aUnDouble = valDe.length == 2 && this.joueurActuel.hasParc() && valDe[0] == valDe[1];


		// Vérifie si un joueur a gagné
		for (Joueur j : tabJoueur)
			if ( j.aGagne() )
				this.joueurGagnant = j;

		if ( this.joueurGagnant == null)
			this.ihm.displayTourJoueur( cptTour, indexFirstPlayer, this.pioche, this.tabJoueur, this.joueurActuel );
		else
			// Affiche la cérémonie du grand vainqueur
			this.ihm.displayFinPartie(this.joueurGagnant, cptTour);
	}
}
