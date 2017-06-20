package minivilles.ihm;

import minivilles.metier.*;
import minivilles.*;
import minivilles.metier.carte.Etablissement;
import java.util.Scanner;
import java.util.ArrayList;


public class IHMConsole
{
	Controleur controler;


	public IHMConsole (Controleur controler)
	{
		this.controler = controler;
	}

	public String displayMenu ()
	{
		System.out.println(	"~ MENU PRINCIPAL ~\n\n"	+
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
	// .*[.[^0-9]].*
	public ArrayList<String> displayChoixJoueurs ()
	{
		System.out.println(	"~ CHOIX DES JOUEURS ~\n" );

		Scanner sc = new Scanner (System.in);
		ArrayList<String> names = new ArrayList<String>();


		int 	cpt = 0;
		String 	ans = "";
		do
		{
			String name = "";
			do
			{
				System.out.print( String.format("%2d - Nom du joueur :  ", (cpt + 1)) );
				name = sc.nextLine();

				if ( name == null || name.matches("") )		System.out.println("\t\tErreur : Nom invalide");
			} while ( name == null || name.matches("") );
			names.add( name );

			cpt++;
			if (cpt >= 2 && cpt < 4)
			{
				{
					System.out.print("-> Voulez-vous rajouter un joueur ? (o/n)  ");
					ans = sc.nextLine();
				} while ( !ans.matches("o|n") );
			}
		} while ( !ans.matches("n") && cpt != 4);

		return names;
	}

	public void displayNouveauTour (Pioche pioche, int numTour)
	{
		this.controler.clearConsole();
		System.out.println( String.format("\t\t--- Tour n°%2d ---\n", numTour) );
		System.out.println( String.format("%s", pioche.toStringNom()) );
	}

	public void displayTourJoueur (Pioche pioche, Joueur[] tabJ, Joueur joueur, int lancerDe)
	{
		Scanner sc = new Scanner(System.in);


		System.out.println( String.format("\n\t--- Tour de %s ---\n. %-15s (%3d)   -> %d", joueur.getPrenom(), joueur.getPrenom(), joueur.getMonnaie(), lancerDe) );
		System.out.println( String.format("%s", joueur.toStringCartes()) );

		//for (Joueur autreJ : tabJ)
		// {
		//	
		// }

		/* Demande de construction */
		String ans = "";
		while ( !ans.matches("n|o") )
		{
			System.out.print( "-> Voulez-vous construire un établissement ? (o/n)  ");
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
					if (e == null)	System.out.println("\tErreur : Argent insuffisant");
				}
				catch (Exception ex)//IndexOutOfBoundsException ex)
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
