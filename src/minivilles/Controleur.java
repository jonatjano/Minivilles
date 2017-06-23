package minivilles;

import minivilles.ihm.Ihm;
import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.metier.carte.Monument;
import minivilles.util.Utility;
import java.util.ArrayList;


public class Controleur
{
	private static int 	MAX_VAL = 6;
	public  static final String PATH = "TEMP";//System.getenv("MiniVille11");

	private Ihm 		ihm;
	private GestionJeu 	gj;


	public Controleur ()
	{
		// this.ihm = new IHMConsole( this );
		this.ihm = new IHMGraphique( this );
	}

	public void lancer ()
	{
		this.ihm.displayMenu();
	}


	public void reponseMenu (String choix)
	{
		ArrayList<String> 	names = null;
		switch (choix.toLowerCase().replace("é","e"))
		{
			case "1":
				this.ihm.displayChoixJoueurs();
				break;
				
			case "evaluation":
				this.ihm.displayChoixPartieInit();
				break;
		}
		if ( !choix.matches("1|-1") )	this.ihm.displayMenu();
	}

	public void reponseChoixJoueurs (ArrayList<String> names)
	{
		if (names != null && names.size() != 0)		this.nouvellePartie(names);
		else										this.ihm.displayChoixJoueurs();
	}

	public void nouvellePartie (ArrayList<String> names)
	{
		this.gj = new GestionJeu(this.ihm, names);
		this.gj.lancer();
	}
	
	public void nouvellePartie (String file)
	{
		this.gj = new GestionJeu(this.ihm, file);
		this.gj.lancer();
	}

	public void reponseTourJoueur (int[] valDe)
	{
		this.gj.resultatTour( valDe );
	}
	

	public int[] lancerDe (int nbDe)
	{
		int[] ret = new int[nbDe];
		for (int i = 0; i < nbDe; i++) {
			ret[i] = (int) (Math.random() * MAX_VAL) + 1;
		}
		return ret;
	}

	public static void main (String[] args)
	{
		Controleur controleur = new Controleur();
		controleur.lancer();
	}

	public Ihm getIhm ()
	{
		return this.ihm;
	}
}
