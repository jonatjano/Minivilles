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

	public String displayMenu ()
	{
		System.out.println(	"~ MENU PRINCIPAL ~\n"		+
							" 1 : Nouvelle partie\n" 	+
							"-1 : Quitter\n"				);

		Scanner sc = new Scanner (System.in);
		String ans = "";
		do
		{
			System.out.print("Choix : ");
			ans = sc.nextLine();

			if ( !ans.matches("-1|1") )	System.out.println("\tErreur : Paramètre incorrect");
		} while ( !ans.matches("-1|1") );

		return ans;
	}

	public ArrayList<String> displayChoixJoueurs ()
	{
		System.out.println(	"~ CHOIX DES JOUEURS ~\n" );

		Scanner sc = new Scanner (System.in);
		ArrayList<String> names = new ArrayList<String>();


		int 	cpt = 0;
		String 	ans = "";
		do
		{
			System.out.print("Nom du joueur :  ");
			names.add( sc.nextLine() );

			if (cpt >= 1 && cpt != 3)
			{
				{
					System.out.print("-> Voulez-vous rajouter un joueur ? (o/n)  ");
					ans = sc.nextLine();
				} while ( !ans.matches("o|n") );
			}
			cpt++;
		} while ( !ans.matches("n") && cpt != 4);

		return names;
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
					e = pioche.achatEtablissement( Integer.parseInt(sc.nextLine()) - 1, joueur);
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

	public int displayChoixDe (int min, int max)
	{
		Scanner sc = new Scanner(System.in);
		String ans = "";

		if (min != max)
		{
			do
			{
				System.out.print("-> Combien de dés voulez-vous utiliser ? (De '" + min + "' à '" + max + "') ");
				ans = sc.nextLine();

				if 		( !ans.matches("[0-9]+") || ans.matches("0*") )						System.out.println("\tErreur : Saisie incorrecte");
				else if ( Integer.parseInt(ans) < min || Integer.parseInt(ans) > max )		System.out.println("\tErreur : Chiffre hors des limites");
			} while ( !ans.matches("[0-9]+") || ans.matches("0*") || Integer.parseInt(ans) < min || Integer.parseInt(ans) > max );
			
			return Integer.parseInt(ans);
		}
		
		return min;
	}
}
