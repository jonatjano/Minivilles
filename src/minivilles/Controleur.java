package minivilles;

import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;


public class Controleur
{
	private IHMConsole ihm;
	

	public Controleur ()
	{
		this.ihm = new IHMConsole();
	}

	public void lancer ()
	{
		String 		choix = "";
		String[] 	names = null;
		do 
		{
			choix = this.ihm.displayMenu();

			switch (choix)
			{
				case "1":
					names = this.ihm.displayChoixJoueurs();
					break;
				case "-1":
					break;
			}
		} while ( choix.matches("-1") );

		if (names != null)
			this.nouvellePartie(names);
	}

	public void nouvellePartie (String... names)
	{
		GestionJeu gj = new GestionJeu(this.ihm, names);
		gj.lancer(0);
	}

	public static void main (String[] args)
	{
		Controleur controleur = new Controleur();
		controleur.lancer();
	}
}
