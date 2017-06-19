package minivilles.ihm;

import minivilles.metier.*;
import minivilles.*;
import minivilles.metier.carte.Etablissement;
import java.util.Scanner;
import java.util.ArrayList;


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

	public void displayJoueur (Pioche pioche, Joueur joueur, int lancerDe)
	{
		Scanner sc = new Scanner(System.in);


		System.out.println( String.format("\n\n%-15s (%3d)   -> %d", joueur.getPrenom(), joueur.getMonnaie(), lancerDe) );
		System.out.println( joueur.toStringCartes() );
		
		/* Demande de construction */
		String ans = "";
		while ( !ans.matches("n|o") )
		{
			System.out.print( "-> Voulez-vous construire un établissement ? (o/n)   ");
			ans = sc.nextLine();
		}

		/* Choix du bâtiment à construire */
		if ( ans.equals("o") )
		{
			ArrayList<Etablissement> etablissements = joueur.getEtablissements();

			System.out.println("\n   Lequel ?");
			System.out.println( pioche.toStringNom() );

			Etablissement e = null;
			while ( e == null )
			{
				System.out.print("-> Entrez l'index : ");
				try
				{
					e = pioche.getEtablissement( Integer.parseInt(sc.nextLine()) - 1 );
				}
				catch (Exception ex)
				{
					System.out.println("\tErreur : Index invalide");
				}
			}

			System.out.println("-> '" + e.getNom() + "' ajouté !");
			joueur.addEtablissement(e);
		}

	}
}
