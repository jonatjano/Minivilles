package minivilles;

import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;


public class GestionJeu
{
	private static int 	MAX_VAL = 6;

	private IHMConsole	ihm;
	private Pioche 		pioche;
	private int 		nbJoueur;
	private Joueur[] 	tabJoueur;
	private int 		banque;
	private Joueur 		joueur;


	public GestionJeu (IHMConsole ihm, String... names)
	{
		this.ihm 		= ihm;
		this.pioche 	= new Pioche();
		this.nbJoueur 	= names.length;
		
		this.tabJoueur = new Joueur[this.nbJoueur];
		for (int i = 0; i < nbJoueur; i++)
			tabJoueur[i] = new Joueur(names[i], 20);	// 3

		this.joueur = null;
	}

	public void lancer (int indexFirstPlayer)
	{
		int cptTour = 0;
		int cpt 	= indexFirstPlayer;
		while (true)
		{
			if (cpt == indexFirstPlayer)
				this.ihm.displayTour(++cptTour);

			this.joueur = this.tabJoueur[cpt];

			System.out.println( String.format("%-15s (%3d)   -> %d", this.joueur.getPrenom(), this.joueur.getMonnaie(), this.lancerDe(1)) );
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
		GestionJeu gestion = new GestionJeu(new IHMConsole(), "Michel", "Jean-Pierre");
		gestion.lancer(0);
	}
}
