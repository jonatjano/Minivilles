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
		this.ihm = new IHMConsole( this );
	}

	public void lancer ()
	{
		String 				choix = "";
		ArrayList<String> 	names = null;
		do 
		{
			this.clearConsole();
			choix = this.ihm.displayMenu();

			switch (choix)
			{
				case "1":
					this.clearConsole();
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

	/**
	  * Nettoie la console pour n'importe quel système d'exploitation
	  */
	public static void clearConsole ()
	{
	    for(int i = 0; i < 50; i++)
		{
			System.out.print( String.format("\033[%dA", 1) ); 	// Avance le curseur de n lignes
			System.out.print( "\033[K" ); 						// Efface la ligne entièrement
		}
	}

	public static void goBack (int i)
	{
		System.out.print( String.format("\033[%dA", i) ); 		// Avance le curseur de n lignes
	}


	public static void main (String[] args)
	{
		Controleur controleur = new Controleur();
		controleur.lancer();
	}
}
