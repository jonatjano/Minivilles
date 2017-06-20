package minivilles;

import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;
import java.util.ArrayList;


public class Controleur
{
	private static int 	MAX_VAL = 6;

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
		boolean bool = true;

		do
		{
			GestionJeu gj = new GestionJeu(this.ihm, names);
			bool = gj.lancer(0);
		} while (bool);
	}

	public int[] lancerDe (int nbDe)
	{
		int[] ret = new int[nbDe];
		for (int i = 0; i < nbDe; i++) {
			ret[i] = (int) (Math.random() * MAX_VAL) + 1;
		}
		return ret;
	}

	/**
	  * Nettoie la console pour n'importe quel système d'exploitation
	  */
	// Avec plusieurs façons de faire
	public static void clearConsole ()
	{
	 //    for(int i = 0; i < 50; i++)
		// {
		// 	System.out.print( String.format("\033[%dA", 1) ); 	// Avance le curseur de n lignes
		// 	System.out.print( "\033[K" ); 						// Efface la ligne entièrement
		// }

		// try 				{ System.console().reader().reset(); }
		// catch (Exception e) {}

		if ( System.getProperty("os.name").startsWith("Windows") )
		{
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
			try 				{ pb.inheritIO().start().waitFor(); }
			catch (Exception e) { e.printStackTrace();				}
		}
		else
			//System.out.println("\u001B[H\u001B[2J");
			System.out.println("\033c");
	}

	public static void goBack (int i)//(int i, int toErase, String line)
	{
		// System.out.print( String.format("\033[%dA", i) ); 		// Avance le curseur de n lignes
		// System.out.print( line + new String(new char[toErase]).replace("\0", " ") + "\n" );
		// System.out.print( String.format("\033[%dA", i) ); 		// Avance le curseur de n lignes

		System.out.print( String.format("\033[%dA", i) ); 		// Avance le curseur de n lignes
		System.out.print( "\033[2K" );
	}


	public static void main (String[] args)
	{
		Controleur controleur = new Controleur();
		controleur.lancer();
	}
}
