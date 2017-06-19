package minivilles.metier;

import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;


public class GestionJeu
{
	private static int 	MAX_VAL = 6;

	private Pioche 		pioche;
	private int 		nbJoueur;
	private Joueur[] 	tabJoueur;
	private int 		banque;
	private Joueur 		joueur;


	public GestionJeu (String... names)
	{
		this.nbJoueur 	= names.length;
		this.pioche 	= new Pioche();

		this.tabJoueur = new Joueur[this.nbJoueur];
		for (int i = 0; i < nbJoueur; i++)
			tabJoueur[i] = new Joueur(names[i], 20);	// 3

		this.joueur = null;
	}

	public void lancer (int indexFirstPlayer)
	{
		for (Joueur j : this.tabJoueur)
			System.out.println(j);
		System.out.println(nbJoueur + "\n");


		int cpt = indexFirstPlayer;
		while (true)
		{
			this.joueur = this.tabJoueur[cpt];

			System.out.println( this.joueur + " -> " + this.lancerDe(1) );
			Utility.waitForSeconds(1.5f);

			cpt++; cpt = cpt%nbJoueur;
		}
	}

	public int lancerDe (int nbDe)
	{
		return (int) (Math.random() * MAX_VAL * nbDe) + 1;
	}


	public static void main (String[] args)
	{
		GestionJeu gestion = new GestionJeu("Michel", "Jean-Pierre");
		gestion.lancer(0);
	}
}
