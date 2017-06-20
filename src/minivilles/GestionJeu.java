package minivilles;

import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;
import java.util.Scanner;
import java.util.ArrayList;


public class GestionJeu
{
	private IHMConsole	ihm;
	private Pioche 		pioche;
	private int 		nbJoueur;
	private Joueur[] 	tabJoueur;
	private int 		banque;
	private Joueur 		joueurGagnant;
	private Joueur 		joueurActuel;


	public GestionJeu (IHMConsole ihm, ArrayList<String> names)
	{
		this.ihm 		= ihm;
		this.pioche 	= new Pioche();
		this.nbJoueur 	= names.size();

		this.tabJoueur = new Joueur[this.nbJoueur];
		for (int i = 0; i < nbJoueur; i++)
			tabJoueur[i] = new Joueur(names.get(i), 20);	// 3

		this.joueurActuel = null;
	}

	public boolean lancer (int indexFirstPlayer)
	{
		int cptTour = 0;
		int cpt 	= indexFirstPlayer;

		while (this.joueurGagnant == null)
		{
			// Nouveau tour
			if (cpt == indexFirstPlayer)
				cptTour++;

			this.joueurActuel = this.tabJoueur[cpt];
			
			this.ihm.displayTourJoueur( cptTour, this.pioche, this.tabJoueur, this.joueurActuel );
			//Utility.waitForSeconds(1.5f);

			cpt++; cpt = cpt%nbJoueur;

			// Vérifie si un joueur a gagné
			for (Joueur j : tabJoueur)
				if ( j.aGagne() )
					this.joueurGagnant = j;
		}

		// Affiche la cérémonie du gagnant
		boolean aRecommence = false;
		if (this.joueurGagnant != null)
		{
			aRecommence = this.ihm.displayFinPartie();
			return aRecommence;
		}
		else
			return aRecommence;
	}
}
