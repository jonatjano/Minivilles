package minivilles.ihm;

import minivilles.metier.*;
import minivilles.util.Utility;
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

				if 		( name == null || !name.matches(".*[a-zA-Z].*") )		System.out.println( "\t\tErreur : Nom invalide" );
				else if ( name.length() > 20 )									System.out.println( "\t\tErreur : Longuer invalide (entre 1 et 20)" );
			} while ( name == null || !name.matches(".*[a-zA-Z].*") || name.length() > 20 );
			names.add( name );

			cpt++;
			if (cpt >= 2 && cpt < 4)
			{
				do
				{
					System.out.print("-> Voulez-vous rajouter un joueur ? (o/n)  ");
					ans = sc.nextLine();

					if ( !ans.matches("o|n") )	System.out.println("\tErreur : Saisie incorrecte");
				} while ( !ans.matches("o|n") );
			}
		} while ( !ans.matches("n") && cpt != 4);

		return names;
	}

	public void displayNouveauTour (Pioche pioche, int numTour)
	{
		this.controler.clearConsole();
		System.out.println( String.format("\t\t--- Tour n°%2d ---\n", numTour) );
		System.out.println( String.format(" - Pioche :\n%s", pioche.toStringNom()) );
	}

	public void displayTourJoueur (int numTour, Pioche pioche, Joueur[] tabJ, Joueur joueurActuel, int lancerDe)
	{
		Scanner sc = new Scanner(System.in);


		// S'il y a une erreur au niveau de la saisie, on réaffiche tout
		String 			ans = "",
						ind = "";
		boolean estChoisi	= false;
		Etablissement 	e 	= null;
		/* AFFICHAGE */
		do
		{
			// Affichage du nouveau tour
			this.displayNouveauTour(pioche, numTour);

			// Affichage des mains des joueurs
			System.out.println("\n\n - Main des joueurs :");
			for (Joueur autreJ : tabJ)
			{
				System.out.println( String.format(". %-20s (%3dP)", autreJ.getPrenom(), autreJ.getMonnaie()) );
				System.out.println( String.format("%s", autreJ.toStringCartes()) );
			}
			System.out.println( String.format("\n\n\t--- Tour de %s ---\n\n. Lancer de dé : %d", joueurActuel.getPrenom(), lancerDe) );

			/* Demande de construction */
			System.out.print( 	"-> Que voulez-vous faire ?\n" 										+ 
								"( 1 : Etablissement ;  2 : Monument ; -1 : Ne rien construire)  " 		);


			if ( !estChoisi )
			{
				ans = sc.nextLine();

				if 		( !ans.matches("n|o") )
				{
					System.out.println("\tErreur : Saisie incorrecte");//this.controler.goBack(1, ans.length(), str);
					Utility.waitForSeconds(0.75f);
					this.controler.goBack(2);
				}
				else if ( ans.equals("o") )
					estChoisi = true;
			}
			else
				System.out.println();


			// S'il a été choisi de construire un établissement
			if ( estChoisi )
			{
				ArrayList<Etablissement> etablissements = joueurActuel.getEtablissements();

				System.out.println("\n   Lequel (Parmi la liste ci-dessous) ?  (NB : '-1' pour revenir en arrière)");
				System.out.println( pioche.toStringNom() );


				System.out.print("\n-> Entrez l'index : ");
				try
				{
					ind = sc.nextLine();
					e 	= pioche.achatEtablissement( Integer.parseInt(ind) - 1, joueurActuel);
					
					if (e == null)
					{
						System.out.println("\tErreur : Argent insuffisant");
						Utility.waitForSeconds(0.75f);
					}
				}
				catch (Exception ex)//IndexOutOfBoundsException ex)
				{
					if ( ind.equals("-1") )											// Si le choix a été de retourner en arrière...
						estChoisi = false;											// Le choix du bâtiment à construire est réinitialisé
					else
					{
						System.out.println("\tErreur : Index invalide");
						Utility.waitForSeconds(0.75f);
					}
					ind = "";
				}

				if (e != null)
				{
					System.out.println("\t-> '" + e.getNom() + "' ajouté !");
					Utility.waitForSeconds(0.75f);
					joueurActuel.addEtablissement(e);
				}
			}
		} while ( !ans.matches("n") && (!ind.matches("[0-9]+") || e == null)  );	// Tant que la réponse n'est pas 'n' ET (que l'indice n'est pas un chiffre (l'index) OU que l'établissement n'est pas nul)


		
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
