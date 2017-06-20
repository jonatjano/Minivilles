package minivilles.ihm;

import java.util.ArrayList;
import java.util.Scanner;

import minivilles.*;
import minivilles.metier.*;
import minivilles.metier.carte.Carte;
import minivilles.metier.carte.Etablissement;
import minivilles.metier.carte.Monument;
import minivilles.util.Utility;


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

	public int[] displayTourJoueur (int numTour, Pioche pioche, Joueur[] tabJ, Joueur joueurActuel)
	{
		Scanner sc = new Scanner(System.in);
		String 			ans = "",
						ind = "";
		Carte 			c 	= null;
		int 			nbDe 	= 0;
		int[]			valDe 	= new int[1];


		int 			cpt = 0;
		/* AFFICHAGE */
		// S'il y a une erreur au niveau de la saisie, on réaffiche tout
		do
		{
			// Affichage du nouveau tour
			this.displayNouveauTour(pioche, tabJ, numTour);


			System.out.println( String.format("\n\n\t--- Tour de %s ---", joueurActuel.getPrenom()) );

			// Seulement pendant la première boucle, les dés sont lancés
			int valDeTot = 0;

			if (cpt == 0)
			{
				// Choix du nombre de dé
				nbDe 	= this.displayChoixDe( 1, joueurActuel.getNbDes() );
				valDe 	= this.controler.lancerDe( nbDe );

				for (int val : valDe)
					valDeTot += val;

				// Action des cartes du joueur en fonction du lancé de dé
				int idJoueurActuel = 0;
				for ( int i = 0; i < tabJ.length; i++ )
					if ( tabJ[i] == joueurActuel ) { idJoueurActuel = i; }

				for ( int i = idJoueurActuel - 1; i != idJoueurActuel; i-- ) {
					if (i < 0) { i = tabJ.length - 1; }
					tabJ[i].actionCartes( joueurActuel,  valDeTot);
				}
			}
			if ( 1 == joueurActuel.getNbDes() )
				System.out.print("\n\n");

			if (valDe.length == 1) {
				System.out.println( String.format("\n. Lancer de dé : %d", valDeTot) );
			}
			else {
				System.out.println( String.format("\n. Lancer de dé : %d (%d + %d)", valDeTot, valDe[0], valDe[1] ) );
			}

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
					String[][] tabAffichageMonument = Monument.getStringAffichage(c.getNom());

					for (int i =tabAffichageMonument.length-1; i >=0 ; i--)
					{
						Controleur.clearConsole();
						String sTemp = "";
						for (int j=tabAffichageMonument.length-1; j >= i ; j--)
							sTemp = String.format("\t%s\n",String.join("",tabAffichageMonument[j])) + sTemp;

						sTemp = String.format("\033[%dB", 4 + i) + sTemp;
						System.out.println(sTemp);
						Utility.waitForSeconds(0.75);
					}

					System.out.println("\t-> '" + c.getNom() + "' construit(e) !");
					Utility.waitForSeconds(3f);
				}
			}
		cpt++;

		} while ( !ans.matches("-1") && (!ind.matches("[0-9]+") || c == null)  );	// Tant que la réponse n'est pas 'n' ET (que l'indice n'est pas un chiffre (l'index) OU que l'établissement n'est pas nul)
		return valDe;
	}

	public boolean displayFinPartie ()
	{
		return false;
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
