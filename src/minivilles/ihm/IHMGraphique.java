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
		this.frame.openPage( new ChoixJoueursMenu(frame) );
	}

	public void displayDebutPartie (Joueur[] tabJ)
	{
		this.frame.openPage( new PartiePanel(frame, tabJ) );
	}

	/**
	  * Mise à jour de l'affichage de la partie
	  */
	public void displayTourJoueur (int numTour, int indexFirstPlayer, Pioche pioche, Joueur[] tabJ, Joueur joueurActuel)
	{
<<<<<<< HEAD
=======
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
			tabJ[ Utility.posModulo(idJoueurActuel - i - 1, tabJ.length) ].actionCartes( joueurActuel,  valDeTot, tabJ);


		toDisplay += "\n\n";
		if (valDe.length == 1)		toDisplay += String.format( "\n. Lancer de dé : %d", valDeTot );
		else 						toDisplay += String.format( "\n. Lancer de dé : %d (%d + %d)", valDeTot, valDe[0], valDe[1] );

>>>>>>> b5be7ec7f936a189e1fb8ee75dd6142a7dba6944

	}

	public void displayFinPartie (Joueur j, int nbTour)
	{
		// Message avec showMessageDialog ?
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
