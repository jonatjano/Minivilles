package minivilles;

import minivilles.ihm.*;
import minivilles.metier.*;


public class GestionJeu
{
	private static int 	MAX_VAL = 6;

	private Pioche 		pioche;
	private Joueur[] 	tabJoueur;
	private int 		banque;


	public GestionJeu (int nbJoueur)
	{
		if (nbJoueur > 0 && nbJoueur <= 4)
		{
			this.pioche = new Pioche();

			this.tabJoueur = new Joueur[nbJoueur];
			for (int i = 0; i < nbJoueur; i++)
				tabJoueur[i] = new Joueur("Michel", 20);	// 3
		}
	}

	public void lancer ()
	{
		for (Joueur j : this.tabJoueur)
			System.out.println(j);
	}

	public int lancerDe (int nbDe)
	{
		return (int) (Math.random() * (MAX_VAL - 1)) + 1;
	}


	public static void main (String[] args)
	{
		GestionJeu gestion = new GestionJeu(2);
		gestion.lancer();
	}
}
