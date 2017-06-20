package minivilles.ihm;

import minivilles.metier.*;
import minivilles.util.Utility;
import minivilles.*;
import minivilles.metier.carte.Etablissement;
import minivilles.metier.carte.Carte;
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

	public void displayNouveauTour (Pioche pioche, Joueur[] tabJ, int numTour)
	{
		this.controler.clearConsole();

		System.out.println( String.format("\t\t--- Tour n°%2d ---\n", numTour) );
		System.out.println( String.format(" - Pioche :\n%s", pioche.toStringNom()) );


		// Affichage des mains des joueurs
		System.out.println("\n\n - Main des joueurs :");
		for (Joueur autreJ : tabJ)
		{
			System.out.println( String.format(". %-20s (%3dP)", autreJ.getPrenom(), autreJ.getMonnaie()) );
			System.out.println( String.format("%s", autreJ.toStringCartes()) );
		}
	}

	public void displayTourJoueur (int numTour, Pioche pioche, Joueur[] tabJ, Joueur joueurActuel)
	{
		Scanner sc = new Scanner(System.in);
		String 			ans = "",
						ind = "";
		Carte 			c 	= null;
		int 			nbDe 	= 0,
						valDe 	= 0;


		int 			cpt = 0;
		/* AFFICHAGE */
		// S'il y a une erreur au niveau de la saisie, on réaffiche tout
		do
		{
			// Affichage du nouveau tour
			this.displayNouveauTour(pioche, tabJ, numTour);
			

			System.out.println( String.format("\n\n\t--- Tour de %s ---", joueurActuel.getPrenom()) );

			// Seulement pendant la première boucle, les dés sont lancés
			if (cpt == 0)
			{
				// Choix du nombre de dé
				nbDe 	= this.displayChoixDe( 1, joueurActuel.getNbDes() );
				valDe 	= this.controler.lancerDe( nbDe );
				

				// Action des cartes du joueur en fonction du lancé de dé
				for ( Joueur j : tabJ )
					j.actionCartes( joueurActuel, valDe );
			}
			if ( 1 == joueurActuel.getNbDes() )
				System.out.print("\n\n");
			

			System.out.println( String.format("\n. Lancer de dé : %d", valDe) );

			/* Demande de construction */
			System.out.print( 	"-> Que voulez-vous faire ?\n" 										+ 
								"   ( 1 : Etablissement ;  2 : Monument ; -1 : Ne rien construire)  " 		);



			if ( !ans.matches("1|2|-1") )
			{
				ans = sc.nextLine();

				if 		( !ans.matches("1|2|-1") )
				{
					System.out.println("\tErreur : Saisie incorrecte");//this.controler.goBack(1, ans.length(), str);
					Utility.waitForSeconds(0.75f);
					this.controler.goBack(2);
				}
			}
			else
				System.out.println();


			/* CONSTRUCTION ETABLISSEMENT */
			if ( ans.matches("1") )
			{
				System.out.println("\n   Lequel (Parmi la liste ci-dessous) ?  (NB : '-1' pour revenir en arrière)");
				System.out.println( pioche.toStringNom() );


				System.out.print("\n-> Entrez l'index : ");
				try
				{
					ind = sc.nextLine();
					c 	= pioche.achatEtablissement( Integer.parseInt(ind) - 1, joueurActuel);
					
					if (c == null)
					{
						System.out.println("\tErreur : Argent insuffisant");
						Utility.waitForSeconds(0.75f);
					}
				}
				catch (Exception ex)//IndexOutOfBoundsException ex)
				{
					if ( ind.equals("-1") )											// Si le choix a été de retourner en arrière...
						ans = "";													// Le choix du bâtiment à construire est réinitialisé
					else
					{
						System.out.println("\tErreur : Index invalide");
						Utility.waitForSeconds(0.75f);
					}
					ind = "";
				}

				if (c != null)
				{
					System.out.println("\t-> '" + c.getNom() + "' ajouté !");
					Utility.waitForSeconds(0.75f);
					joueurActuel.addEtablissement((Etablissement) c);
				}
			}

			/* CONSTRUCTION MONUMENT */
			if ( ans.matches("2") )
			{
				System.out.println("\n   Lequel (Parmi la liste ci-dessous) ?  (NB : '-1' pour revenir en arrière)");
				System.out.println( joueurActuel.toStringMonumentsNonAchetes() );


				System.out.print("\n-> Entrez l'index : ");
				try
				{
					ind = sc.nextLine();
					c 	= joueurActuel.construireMonument( Integer.parseInt(ind) - 1 );
					
					if (c == null)
					{
						System.out.println("\tErreur : Argent insuffisant / Déjà construit");
						Utility.waitForSeconds(0.75f);
					}
				}
				catch (Exception ex)//IndexOutOfBoundsException ex)
				{
					if ( ind.equals("-1") )											// Si le choix a été de retourner en arrière...
						ans = "";													// Le choix du bâtiment à construire est réinitialisé
					else
					{
						System.out.println("\tErreur : Index invalide");
						Utility.waitForSeconds(0.75f);
					}
					ind = "";
				}

				if (c != null)
				{
					System.out.println("\t-> '" + c.getNom() + "' construit(e) !");
					Utility.waitForSeconds(0.75f);
				}
			}
		cpt++;

		} while ( !ans.matches("-1") && (!ind.matches("[0-9]+") || c == null)  );	// Tant que la réponse n'est pas 'n' ET (que l'indice n'est pas un chiffre (l'index) OU que l'établissement n'est pas nul)
	}

	public int displayChoixDe (int min, int max)
	{
		Scanner sc = new Scanner(System.in);
		String ans = "";

		if (min != max)
		{
			do
			{
				System.out.print("\n-> Combien de dés voulez-vous utiliser ? (De '" + min + "' à '" + max + "') ");
				ans = sc.nextLine();

				if 		( !ans.matches("[0-9]+") || ans.matches("0*") )						System.out.println("\tErreur : Saisie incorrecte");
				else if ( Integer.parseInt(ans) < min || Integer.parseInt(ans) > max )		System.out.println("\tErreur : Chiffre hors des limites");
			} while ( !ans.matches("[0-9]+") || ans.matches("0*") || Integer.parseInt(ans) < min || Integer.parseInt(ans) > max );
			
			return Integer.parseInt(ans);
		}
		
		return min;
	}
}
