package minivilles;

import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;
import java.util.Scanner;
import java.util.ArrayList;


public class GestionJeu
{
	private static int 	MAX_VAL = 6;

	private IHMConsole	ihm;
	private Pioche 		pioche;
	private int 		nbJoueur;
	private Joueur[] 	tabJoueur;
	private int 		banque;
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

	public void lancer (int indexFirstPlayer)
	{
		int cptTour = 0;
		int cpt 	= indexFirstPlayer;
		while (true)
		{
			// Nouveau tour
			if (cpt == indexFirstPlayer)
			{
				this.ihm.displayTour(this.pioche, ++cptTour);
			}

			this.joueurActuel = this.tabJoueur[cpt];

			//~ CHANGER L'ORDRE
			int nbDe = this.ihm.displayChoixDe( 1, this.joueurActuel.getNbDes() );
			int valDe = this.lancerDe( nbDe );
			for ( Joueur j : this.tabJoueur ) {
				j.actionCartes( this.joueurActuel, valDe );
			}
			this.ihm.displayJoueur( this.pioche, this.joueurActuel, valDe );
			//Utility.waitForSeconds(1.5f);

			cpt++; cpt = cpt%nbJoueur;
		}
	}

	public int lancerDe (int nbDe)
	{
		return (int) (Math.random() * MAX_VAL * nbDe) + 1;
	}
}
