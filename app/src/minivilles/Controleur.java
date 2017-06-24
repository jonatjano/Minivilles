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
	public  static final String PATH = System.getenv("MINIVILLE11");

	private Ihm 		ihm;
	private GestionJeu 	gj;


	public Controleur (boolean gui)
	{
		if (gui)
			this.ihm = new IHMGraphique( this );
		else
			this.ihm = new IHMConsole( this );
	}

	public void lancer ()
	{
		this.ihm.displayMenu();
	}

	public boolean isEvaluationMode() {return gj.isEvaluationMode();}

	public void reponseMenu (String choix)
	{
		ArrayList<String> 	names = null;
		switch (choix.toLowerCase().replace("é","e"))
		{
			case "1":
				this.ihm.displayChoixJoueurs();
				break;

			case "evaluation":
				this.ihm.displayChoixPartieInit(true);
				break;
			case "2":
				this.ihm.displayChoixPartieLoad();
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

	public void nouvellePartie (String file , boolean ev)
	{
		this.gj = new GestionJeu(this.ihm, file , ev);
		this.gj.lancer();
	}

	public void reponseTourJoueur (int[] valDe)
	{
		this.gj.resultatTour( valDe );
	}
	
	public void sauvegarde(String file)
	{
		this.gj.save (file);
	}

	public void activateCardsAction (int valDeTotale)
	{
		this.gj.activateCardsAction( valDeTotale );
	}


	public int[] lancerDe (int nbDe)
	{
		int[] ret = new int[nbDe];
		for (int i = 0; i < nbDe; i++) {
			ret[i] = (int) (Math.random() * MAX_VAL) + 1;
		}
		return ret;
	}

	public Ihm getIhm ()
	{
		return this.ihm;
	}

	public static void main (String[] args)
	{
		boolean gui = false;
		if (args.length >= 1 && args[0].equalsIgnoreCase("GUI"))
			gui = true;
		Controleur controleur = new Controleur(gui);
		controleur.lancer();
	}
}
