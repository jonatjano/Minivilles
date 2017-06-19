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

	public void nouvellePartie (String... names)
	{
		GestionJeu gj = new GestionJeu(this.ihm, names);
		gj.lancer(0);
	}

	public static void main (String[] args)
	{
		Controleur controleur = new Controleur();
		
		String[] names = this.ihm.displayMenu();
		if (names != null)
			this.nouvellePartie(names);
	}
}
