package minivilles;

import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;
import java.util.ArrayList;


public class Controleur
{
	private IHMConsole ihm;
	

	public Controleur ()
	{
		this.ihm = new IHMConsole();
	}

	public void lancer ()
	{
		String 				choix = "";
		ArrayList<String> 	names = null;
		do 
		{
			choix = this.ihm.displayMenu();

			switch (choix)
			{
				case "1":
					names = this.ihm.displayChoixJoueurs();
					break;
			}
		} while ( !choix.matches("-1") && names == null );
		
		if (names != null && names.size() != 0)
			this.nouvellePartie(names);
	}

	public void nouvellePartie (ArrayList<String> names)
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
