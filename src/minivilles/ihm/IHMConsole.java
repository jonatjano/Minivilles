package minivilles.ihm;

import minivilles.metier.*;
import minivilles.*;
import java.util.Scanner;


public class IHMConsole
{
	public IHMConsole ()
	{

	}

	public void displayTour (Pioche pioche, int numTour)
	{
		System.out.println( String.format("\n\n\t--- Tour n°%2d ---\n", numTour) );
		System.out.println( pioche.toStringNom() );
	}

	public void displayJoueur (Joueur joueur, int lancerDe)
	{
		Scanner sc = new Scanner(System.in);


		System.out.println( String.format("%-15s (%3d)   -> %d", this.joueur.getPrenom(), this.joueur.getMonnaie(), lancerDe );
		System.out.println( this.joueur.toStringCartes() );
		
		/* Demande de construction */
		String ans = "";
		while ( !ans.matches("n|o") )
		{
			System.out.println( "Voulez-vous construire un établissement ? (o/n)");
			ans = sc.nextLine();
		}

		if ( ans.equals("o") )
		{
			System.out.println("Lequel ?");

		}

		sc.close();
	}
}
