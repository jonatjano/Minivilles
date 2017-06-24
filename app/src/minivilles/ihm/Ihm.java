package minivilles.ihm;

import minivilles.Controleur;
import minivilles.GestionJeu;
import minivilles.metier.*;
import minivilles.metier.carte.*;
import java.util.ArrayList;


public abstract class Ihm
{
	protected Controleur controler;
	

	public abstract void 				displayMenu ();
	public abstract boolean 			displayDemande (String demande);
	public abstract void			 	displayChoixJoueurs ();
	public abstract void			 	displayChoixPartieInit(boolean ev);
	public abstract void			 	displayChoixPartieLoad();
	public abstract void 				displayDebutPartie (GestionJeu gj);
	public abstract void 				displayTourJoueur (GestionJeu gj);
	public abstract void 				displayFinPartie (Joueur j, int nbTour);
	public abstract int 				displayChoixDe (int min, int max);
	public abstract Joueur				displaychoixJoueur(String demande, String err, Joueur[] tabJ);
	public abstract Etablissement 		displaychoixJoueur(String demande, String err, Etablissement[] tabE);

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

		// System.out.println("\u001B[H\u001B[2J");

		if ( System.getProperty("os.name").startsWith("Windows") )
		{
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
			try 				{ pb.inheritIO().start().waitFor(); }
			catch (Exception e) { e.printStackTrace();				}
		}
		else
			System.out.println("\033c");

		String[][] sos = Monument.getStringAffichage("Gare");
	}

	public static void goBack (int i, int toErase, String line)//(int i, int toErase, String line)
	{
		System.out.print( String.format("\033[%dA", i) ); 		// Avance le curseur de n lignes
		System.out.print( line + new String(new char[toErase]).replace("\0", " ") + "\n" );
		System.out.print( String.format("\033[%dA", 1) ); 		// Avance le curseur de n lignes
		System.out.print( line );
	}
}
