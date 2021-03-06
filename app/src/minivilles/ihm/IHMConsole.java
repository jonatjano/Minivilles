package minivilles.ihm;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

import minivilles.*;
import minivilles.ihm.Ihm;
import minivilles.metier.*;
import minivilles.metier.carte.Carte;
import minivilles.metier.carte.Etablissement;
import minivilles.metier.carte.Monument;
import minivilles.util.Utility;


public class IHMConsole extends Ihm
{
	public IHMConsole (Controleur controler)
	{
		this.controler = controler;
	}

	public void displayMenu ()
	{
		Ihm.clearConsole();
		System.out.println(	"~ MENU PRINCIPAL ~\n\n"	+
							" 1 : Nouvelle partie\n" 	+
							" 2 : Charger une partie\n" 	+
							"-1 : Quitter\n"				);

		Scanner sc = new Scanner (System.in);
		String ans = "";
		do
		{
			System.out.print("Choix : ");
			ans = sc.nextLine();

			if ( !ans.toLowerCase().replace("é","e").matches("-1|1|2|(evaluation)") )	System.out.println("\tErreur : Paramètre incorrect");
		} while ( !ans.toLowerCase().replace("é","e").matches("-1|1|2|(evaluation)") );

		this.controler.reponseMenu( ans );
	}

	public void displayChoixJoueurs ()
	{
		Ihm.clearConsole();
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
				if 		( name == null || !name.matches("[.[^;]]*[a-zA-Z][.[^;]]*") )		System.out.println( "\t\tErreur : Nom invalide" );
				else if ( name.length() > 20 )									System.out.println( "\t\tErreur : Longuer invalide (entre 1 et 20)" );
				else if ( Utility.containsIgnoreCase(names,name))				System.out.println( "\t\tErreur : Un nom identique existe déjà" );
			} while ( name == null || !name.matches("[.[^;]]*[a-zA-Z][.[^;]]*") || name.length() > 20 || Utility.containsIgnoreCase(names,name));
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

		this.controler.reponseChoixJoueurs( names );
	}
	
	public void displayChoixPartieInit (boolean ev)
	{
		Ihm.clearConsole();
		System.out.println(	"~ CHOIX DU FICHIER D'INITIALISATION ~\n" );

		Scanner sc = new Scanner (System.in);
		String[] fPartieInit;
		String choix;
		String err = null;
		
		do
		{
			fPartieInit = new File(Controleur.PATH + "/PartieInit").list();
			for (int i=0; i< fPartieInit.length; i++)
				System.out.println("\t" + i + " -" + fPartieInit[i]);
			
			System.out.print("\nVeuillez entrer l'index du fichier à charger : ");
			choix = sc.nextLine();
			
			if (!choix.matches("[0-9]+") && !choix.matches("0*"))															System.out.println("Ce n'est pas un nombre");
			if (choix.matches("[0-9]+") && ( Integer.parseInt(choix) < 0 || Integer.parseInt(choix) >= fPartieInit.length))	System.out.println("Indice invalide");
		} while((!choix.matches("[0-9]+") && !choix.matches("0*")) ||
				Integer.parseInt(choix) < 0 || Integer.parseInt(choix) >= fPartieInit.length);		
		
		
		this.controler.nouvellePartie( fPartieInit[Integer.parseInt(choix)] , ev);
	}
	
	public void displayChoixPartieLoad ()
	{
		Ihm.clearConsole();
		System.out.println(	"~ CHOIX DU FICHIER À CHARGER ~\n" );

		Scanner sc = new Scanner (System.in);
		String[] fPartieSave;
		String choix;
		String err = null;
		
		do
		{
			fPartieSave = new File(Controleur.PATH + "/Sauvegarde").list();
			if (fPartieSave.length == 0)
			{
				System.out.println("pas de fichier de sauvegarde !");
				Utility.waitForSeconds(1);
				this.displayMenu();
			}
			
			for (int i=0; i< fPartieSave.length; i++)
				System.out.println("\t" + i + " -" + fPartieSave[i]);
			
			System.out.print("\nVeuillez entrer l'index du fichier à charger : ");
			choix = sc.nextLine();
			
			if (!choix.matches("[0-9]+") && !choix.matches("0*"))															System.out.println("Ce n'est pas un nombre");
			if (choix.matches("[0-9]+") && ( Integer.parseInt(choix) < 0 || Integer.parseInt(choix) >= fPartieSave.length))	System.out.println("Indice invalide");
		} while((!choix.matches("[0-9]+") && !choix.matches("0*")) ||
				Integer.parseInt(choix) < 0 || Integer.parseInt(choix) >= fPartieSave.length);		
		
		
		this.controler.nouvellePartie( fPartieSave[Integer.parseInt(choix)] , false);
	}
	
	public boolean displayDemande (String demande)
	{
		if ( demande == null )
			return false;
		
		demande +=  "(o/n)";
		Scanner sc = new Scanner(System.in);
		
		System.out.println(demande);
		String s = sc.nextLine();
		while(!s.matches("o|n"))
		{
			System.out.println("réponse invalide : (o/n)");
			s = sc.nextLine();
		}
		
		System.out.println(s == "o" ? true : false);
		return s.equals("o") ? true : false;
	}

	public void displayDebutPartie (GestionJeu gj)
	{
		Ihm.clearConsole();
		// Affichage de début de partie (optionnel)
		// A voir par la suite
		// System.out.println("Appuyez sur Entrée pour commencer...");
		// System.in.read();
	}

	public String displayNouveauTour (GestionJeu gj)
	{
		String sRet = "";

		sRet += String.format("\t\t--- Tour n°%2d ---\n", gj.getNumTour());
		sRet += String.format("\n - Pioche :\n%s", gj.getPioche().toStringNom());


		// Affichage des mains des joueurs
		sRet += "\n\n - Main des joueurs :";
		for (Joueur autreJ : gj.getTabJoueur())
		{
			sRet += String.format("\n. %-20s (%3dP)", autreJ.getPrenom(), autreJ.getMonnaie());
			sRet += String.format("\n%s", autreJ.toStringCartes());
		}

		return sRet;
	}

	public void displayTourJoueur (GestionJeu gj)
	{
		Scanner sc 	= new Scanner(System.in);

		int 		numTour 			= gj.getNumTour(),
					indexFirstPlayer 	= gj.getIndexFirstPlayer();
		Pioche 		pioche 				= gj.getPioche();
		Joueur[] 	tabJ 				= gj.getTabJoueur();
		Joueur 		joueurActuel 		= gj.getJoueurActuel();

		String 	ans 	= "",
				choix 	= "";
		Carte 	c 	= null;
		int 	nbDe 		= 0,
				valDeTot 	= 0;
		int[]	valDe 		= new int[1];
		String toDisplay = "";
		toDisplay += this.displayNouveauTour(gj);
		toDisplay += String.format("\n\n\t--- Tour de %s ---", joueurActuel.getPrenom());


		Ihm.clearConsole();
		System.out.println( toDisplay );

		/* LANCEMENT n°1 */
		// Choix du nombre de dé
		nbDe 	= this.displayChoixDe( 1, joueurActuel.getNbDes() );
		
		if ( this.controler.isEvaluationMode())
		{
			valDe = new int[nbDe];
			for (int i = 1 ; i <= nbDe ; i++)
			{
				String s = "";
				do
				{
					System.out.print("valeur du dé " + i + " : ");
					s = sc.nextLine();
					if (!s.matches("[0-9]+"))																	System.out.println("Ce n'est pas un nombre !");
					if (s.matches("[0-9]+") && ( Integer.parseInt(s) <= 0 || Integer.parseInt(s) > 6 ))	System.out.println("nombre invalide !");
				} while (!s.matches("[0-9]+") || Integer.parseInt(s) <= 0 || Integer.parseInt(s) > 6);
				valDe[i-1] = Integer.parseInt(s);
				System.out.println("");
			}
		}
		else
			valDe 	= this.controler.lancerDe( nbDe );

		valDeTot = 0;
		for (int val : valDe)
			valDeTot += val;

		if ( joueurActuel.hasTourRadio() )
		{
			do
			{
				Ihm.clearConsole();
				System.out.println( toDisplay );
				if (valDe.length == 1)		System.out.println( String.format( "\n. Lancer de dé : %d", valDeTot ) );
				else 						System.out.println( String.format( "\n. Lancer de dé : %d (%d + %d)", valDeTot, valDe[0], valDe[1] ) );

				if (controler.isEvaluationMode())
					ans = "n";
				else
				{
					System.out.print("Voulez-vous relancer le(s) dés ? (o/n)  ");
					ans = sc.nextLine();
					if ( !ans.matches("o|n") )
					{
						System.out.println("\tErreur : Saisie incorrecte");
						Utility.waitForSeconds(0.75f);
					}
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

		this.controler.activateCardsAction(valDeTot);
		toDisplay = this.displayNouveauTour(gj) + String.format("\n\n\t--- Tour de %s ---", joueurActuel.getPrenom());
		Ihm.clearConsole();
		System.out.println( toDisplay );
		

		toDisplay += "\n\n";
		if (valDe.length == 1)		toDisplay += String.format( "\n. Lancer de dé : %d", valDeTot );
		else 						toDisplay += String.format( "\n. Lancer de dé : %d (%d + %d)", valDeTot, valDe[0], valDe[1] );


		/* Demande de construction */
		toDisplay +=	"\n-> Que voulez-vous faire ?\n" 										+
						"   ( 1 : Etablissement ;  2 : Monument ; -1 : Ne rien construire\n"	+
						"     4 : Sauvegarder et quitter\n"										+
						"     3 : Glossaire des cartes (avec effet)                       )  ";
		do
		{
			choix = ""; c = null;
			do
			{
				Ihm.clearConsole();
				System.out.print( toDisplay );
				choix = sc.nextLine();

				if ( !choix.matches("1|2|3|4|-1") )//&& !choix.equals("") )
				{
					System.out.println("\tErreur : Saisie incorrecte");
					Utility.waitForSeconds(0.75f);
				}
			} while ( !choix.matches("1|2|3|4|-1") );


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
						if (joueurActuel.addEtablissement((Etablissement) c))
							System.out.println("\t-> '" + c.getNom() + "' ajouté !");
						else
							System.out.println("\t-> '" + c.getNom() + "' : Limite atteinte !");
						Utility.waitForSeconds(0.75f);
						
							
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
				printCartes(pioche);
			}
			
			if ( choix.matches("4") )
			{
				Ihm.clearConsole();
				
				File fichierSave = null;
				String nomFichier;
				
				do
				{
					System.out.print("Entrez un nom de sauvegarde : ");
					nomFichier = sc.nextLine();
					
					if 	( nomFichier == null || !nomFichier.matches("[a-z0-9-_A-Z]+") )	
						System.out.println( "\t\tErreur : Nom de fichier invalide (Caractère autoriser : lettre sans accent, chiffre, tiret du milieu, tiret du bas)" );
					else
					{
						fichierSave = new File(Controleur.PATH + "/Sauvegarde/" + nomFichier + ".save");
						if 		(fichierSave.exists())
							System.out.println("Une sauvegarde avec ce nom existe déjà");
						else if ( nomFichier.length() > 20 )							System.out.println( "\t\tErreur : Longuer invalide (entre 1 et 20)" );
					}
					
				} while (nomFichier == null || !nomFichier.matches("[a-z0-9-_A-Z]+") || fichierSave.exists() || nomFichier.length() > 20);
				
				this.controler.sauvegarde(nomFichier + ".save");
				Ihm.clearConsole();
				System.out.println("La partie a été sauvegarder sous le nom : " + nomFichier);
				Utility.waitForSeconds(2);
				return;
			}

		} while ( (ans.equals("") && !choix.equals("-1")) || ans.equals("-1")  );

		this.controler.reponseTourJoueur( valDe );
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
		System.out.println( "\n\tAppuyez sur Entrée..." );

		try					{ System.in.read();System.in.skip(1); }
		catch (Exception e)	{}

		this.controler.lancer();
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

	public Joueur displaychoixJoueur(String demande, String err, Joueur[] tabJ)
	{
		Ihm.clearConsole();
		if ( err != null )
			System.out.println(err);

		String ans = "";
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println(demande);
			for (int i = 0;i < tabJ.length ;i++ )
				System.out.println("\t" + i + " -" + tabJ[i].getPrenom());
				
			ans = sc.nextLine();
			Ihm.clearConsole();
			if (!ans.matches("[0-9]+") && !ans.matches("0*"))													System.out.println("Ce n'est pas un nombre");
			if (ans.matches("[0-9]+") && ( Integer.parseInt(ans) < 0 || Integer.parseInt(ans) >= tabJ.length))	System.out.println("Indice invalide");
		} while(!ans.equals("-1") && ((!ans.matches("[0-9]+") && !ans.matches("0*")) ||
				Integer.parseInt(ans) < 0 || Integer.parseInt(ans) >= tabJ.length));
				
		if (ans.equals("-1"))
			return null;
		
		return tabJ[Integer.parseInt(ans)];

	}

	public Etablissement displaychoixJoueur(String demande, String err, Etablissement[] tabE)
	{
		Ihm.clearConsole();
		if ( err != null )
			System.out.println(err);

		Scanner sc = new Scanner(System.in);
		String ans = "";
		
		do
		{
			System.out.println(demande);
			for (int i = 0;i < tabE.length ;i++ )
				System.out.println("\t" + i + " -" + tabE[i].getNom());
				
			ans = sc.nextLine();
			Ihm.clearConsole();
			if (!ans.matches("[0-9]+") && !ans.matches("0*"))						System.out.println("Ce n'est pas un nombre");
			if (ans.matches("[0-9]+") && ( Integer.parseInt(ans) < 0 || Integer.parseInt(ans) >= tabE.length))	System.out.println("Indice invalide");
		} while(!ans.equals("-1") && ((!ans.matches("[0-9]+") && !ans.matches("0*")) ||
				Integer.parseInt(ans) < 0 || Integer.parseInt(ans) >= tabE.length));

		if (ans.equals("-1"))
			return null;
		
		return tabE[Integer.parseInt(ans)];

	}

	public void printCartes (Pioche pioche)
	{
		Ihm.clearConsole();
		Utility.waitForSeconds(0.25f);
		Scanner pause = new Scanner(System.in);

		Etablissement[] cartes = pioche.getCartes();

		for (int i = 0; i < cartes.length / 2; i++)
		{
			System.out.println(cartes[i].toString() + "\n");
		}
		System.out.println( "\n\tAppuyez sur Entrée pour voir la suite..." );
		try					{ System.in.read(); }
		catch (Exception e)	{}

		Ihm.clearConsole();

		Utility.waitForSeconds(0.25f);
		for (int i = cartes.length / 2; i < cartes.length; i++)
		{
			System.out.println(cartes[i].toString() + "\n");
		}

		System.out.println( "\n\tAppuyez sur une Entrée pour voir la suite..." );
		try					{ System.in.skip(1); System.in.read();System.in.skip(1); }
		catch (Exception e)	{}

		Utility.waitForSeconds(0.25f);
		Ihm.clearConsole();
	}
}
