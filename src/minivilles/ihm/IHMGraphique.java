package minivilles.ihm;

import java.util.Scanner;
import java.util.ArrayList;

import minivilles.*;
import minivilles.ihm.Ihm;
import minivilles.ihm.gui.*;
import minivilles.metier.*;
import minivilles.metier.carte.Carte;
import minivilles.metier.carte.Etablissement;
import minivilles.metier.carte.Monument;
import minivilles.util.Utility;


public class IHMGraphique extends Ihm
{
	MainFrame frame;


	public IHMGraphique (Controleur controler)
	{
		this.controler 	= controler;
		this.frame 		= new MainFrame( this.controler );
	}

	public void displayMenu ()
	{
		this.frame.openPage( new MainMenu(frame) );
	}

	public void displayChoixJoueurs ()
	{
		this.frame.openPage(  new ChoixJoueursMenu(frame) );
	}

	public String displayNouveauTour (Pioche pioche, Joueur[] tabJ, int numTour)
	{
		String sRet = "";

		sRet += String.format("\t\t--- Tour n°%2d ---\n", numTour);
		sRet += String.format("\n - Pioche :\n%s", pioche.toStringNom());


		// Affichage des mains des joueurs
		sRet += "\n\n - Main des joueurs :";
		for (Joueur autreJ : tabJ)
		{
			sRet += String.format("\n. %-20s (%3dP)", autreJ.getPrenom(), autreJ.getMonnaie());
			sRet += String.format("\n%s", autreJ.toStringCartes());
		}

		return sRet;
	}

	public void displayTourJoueur (int numTour, int indexFirstPlayer, Pioche pioche, Joueur[] tabJ, Joueur joueurActuel)
	{
		Scanner sc 	= new Scanner(System.in);
		String 	ans 	= "",
				choix 	= "";
		Carte 	c 	= null;
		int 	nbDe 		= 0,
				valDeTot 	= 0;
		int[]	valDe 		= new int[1];
		String toDisplay = "";
		toDisplay += this.displayNouveauTour(pioche, tabJ, numTour);
		toDisplay += String.format("\n\n\t--- Tour de %s ---", joueurActuel.getPrenom());


		Ihm.clearConsole();
		System.out.println( toDisplay );

		/* LANCEMENT n°1 */
		// Choix du nombre de dé
		nbDe 	= this.displayChoixDe( 1, joueurActuel.getNbDes() );
		valDe 	= this.controler.lancerDe( nbDe );

		valDeTot = 0;
		for (int val : valDe)
			valDeTot += val;

		boolean peutRelancer = false;
		if ( joueurActuel.hasTourRadio() )
		{
			do
			{
				Ihm.clearConsole();
				System.out.println( toDisplay );
				if (valDe.length == 1)		System.out.println( String.format( "\n. Lancer de dé : %d", valDeTot ) );
				else 						System.out.println( String.format( "\n. Lancer de dé : %d (%d + %d)", valDeTot, valDe[0], valDe[1] ) );

				System.out.print("Voulez-vous relancer le(s) dés ? (o/n)  ");
				ans = sc.nextLine();

				if ( !ans.matches("o|n") )
				{
					System.out.println("\tErreur : Saisie incorrecte");
					Utility.waitForSeconds(0.75f);
				}
			} while ( !ans.matches("o|n") );
		}

		/* LANCEMENT n°2 (Ou pas) */
		if ( ans.matches("o") )
		{
			Ihm.clearConsole();
			System.out.println( toDisplay );

			// Choix du nombre de dé
			nbDe 	= this.displayChoixDe( 1, joueurActuel.getNbDes() );
			valDe 	= this.controler.lancerDe( nbDe );

			valDeTot = 0;
			for (int val : valDe)
				valDeTot += val;
		}

		// Action des cartes du joueur en fonction du lancé de dé
		int idJoueurActuel = 0;
		for (int i = 0; i < tabJ.length; i++)
			if ( tabJ[i] == joueurActuel )
				idJoueurActuel = i;

		// Une fois que le lancer est définitif...
		// Active les actions pour les joueurs en commençant par le premier et en allant dans le sens inverse des aiguilles d'une montre
		for (int i = 0; i < tabJ.length; i++) //int i = idJoueurActuel - 1; i != idJoueurActuel; i-- )
			tabJ[ Utility.posModulo(idJoueurActuel - i - 1, tabJ.length) ].actionCartes( joueurActuel,  valDeTot);


		toDisplay += "\n\n";
		if (valDe.length == 1)		toDisplay += String.format( "\n. Lancer de dé : %d", valDeTot );
		else 						toDisplay += String.format( "\n. Lancer de dé : %d (%d + %d)", valDeTot, valDe[0], valDe[1] );


		/* Demande de construction */
		toDisplay +=	"\n-> Que voulez-vous faire ?\n" 										+
						"   ( 1 : Etablissement ;  2 : Monument ; -1 : Ne rien construire\n"	+
						"     3 : Glossaire des cartes (avec effet)                       )  ";
		do
		{
			choix = ""; c = null;
			do
			{
				Ihm.clearConsole();
				System.out.print( toDisplay );

				choix = sc.nextLine();

				if ( !choix.matches("1|2|3|-1") )
				{
					System.out.println("\tErreur : Saisie incorrecte");
					Utility.waitForSeconds(0.75f);
				}
			} while ( !choix.matches("1|2|3|-1") );


			/* CONSTRUCTION ETABLISSEMENT */
			ans = "";
			if ( choix.matches("1") )
			{
				ans = "";
				do
				{
					Ihm.clearConsole();
					System.out.println( toDisplay );

					System.out.println("\n   Lequel (Parmi la liste ci-dessous) ?  (NB : '-1' pour revenir en arrière)");
					System.out.println( pioche.toStringNom() );

					try
					{
						System.out.print("\n-> Entrez l'index : ");
						ans = sc.nextLine();
						c 	= pioche.achatEtablissement( Integer.parseInt(ans) - 1, joueurActuel);

						if (c == null)
						{
							System.out.println("\tErreur : Argent insuffisant");
							Utility.waitForSeconds(0.75f);
						}
					}
					catch (Exception ex)//IndexOutOfBoundsException ex)
					{
						if ( !ans.equals("-1") )
						{
							System.out.println("\tErreur : Index invalide");
							Utility.waitForSeconds(0.75f);
						}
					}

					if (c != null)
					{
						System.out.println("\t-> '" + c.getNom() + "' ajouté !");
						Utility.waitForSeconds(0.75f);
						joueurActuel.addEtablissement((Etablissement) c);
					}
				} while ( !ans.equals("-1") && c == null );
			}

			/* CONSTRUCTION MONUMENT */
			if ( choix.matches("2") )
			{
				do
				{
					Ihm.clearConsole();
					System.out.println( toDisplay );

					System.out.println("\n   Lequel (Parmi la liste ci-dessous) ?  (NB : '-1' pour revenir en arrière)");
					System.out.println( joueurActuel.toStringMonumentsNonAchetes() );

					try
					{
						System.out.print("\n-> Entrez l'index : ");
						ans = sc.nextLine();
						c 	= joueurActuel.construireMonument( Integer.parseInt(ans) - 1 );

						if (c == null)
						{
							System.out.println("\tErreur : Argent insuffisant / Déjà construit");
							Utility.waitForSeconds(0.75f);
						}

					}
					catch (Exception ex)//IndexOutOfBoundsException ex)
					{
						if ( !ans.equals("-1") )
						{
							System.out.println("\tErreur : Index invalide");
							Utility.waitForSeconds(0.75f);
						}
					}

					if (c != null)
					{
						String[][] tabAffichageMonument = Monument.getStringAffichage( c.getNom() );

						for (int i = tabAffichageMonument.length - 1; i >= 0; i--)
						{
							Ihm.clearConsole();
							String sTemp = "";
							for (int j=tabAffichageMonument.length-1; j >= i ; j--)
								sTemp = String.format("\t%s\n",String.join("",tabAffichageMonument[j])) + sTemp;

							sTemp = String.format("\033[%dB", 4 + i) + sTemp;
							System.out.println(sTemp);
							Utility.waitForSeconds(0.30);
						}

						System.out.println("\t-> '" + c.getNom() + "' construit(e) !");
						Utility.waitForSeconds(1.25f);
					}
				} while ( !ans.equals("-1") && c == null );
			}

			if ( choix.matches("3") )
			{
				Ihm.clearConsole();
				Utility.waitForSeconds(0.25f);
				System.out.println(pioche.toStringCartes());

				System.out.println( "\n\tAppuyez sur une touche..." );
				try					{ System.in.read(); }
				catch (Exception e)	{}
				Ihm.clearConsole();
			}
		} while ( (ans.equals("") && !choix.equals("-1")) || ans.equals("-1")  );
	}

	public void displayFinPartie (Joueur j, int nbTour)
	{
		// String ans = "";
		// do
		// {
		// } while ();
		// return bool;
		Ihm.clearConsole();

		System.out.println( "~ NOUS AVONS UN GAGNANT !" );
		System.out.println( String.format("\nBravo à %s pour sa victoire en %d tours !", j.getPrenom(), nbTour) );
		System.out.println( "Félicitations !" );
		System.out.println( "\n\tAppuyez sur une touche..." );

		try					{ System.in.read(); }
		catch (Exception e)	{}
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
