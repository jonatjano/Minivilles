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

	private Ihm ihm;


	public Controleur ()
	{
		this.ihm = new IHMConsole( this );
		// this.ihm = new IHMGraphique( this );
	}

	public void lancer ()
	{
		String 				choix = "";
		ArrayList<String> 	names = null;

		this.ihm.displayMenu();
	}


	public void reponseMenu (int choix)
	{
		if ( !choix.matches("1|-1") ) { return ; }

		switch (choix)
		{
			case "1":
				names = this.ihm.displayChoixJoueurs();
				break;
		}

		if ( !choix.matches("-1") && names == null ) {lancer()};

		if (names != null && names.size() != 0)
			this.nouvellePartie(names);	
	}

	public void nouvellePartie (ArrayList<String> names)
	{
		boolean bool = true;

		do
		{
			GestionJeu gj = new GestionJeu(this.ihm, names);
			bool = gj.lancer(0);
		} while (bool);

		this.lancer();
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
}
