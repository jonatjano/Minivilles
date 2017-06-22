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

	public void displayDebutPartie (GestionJeu gj)
	{
		this.frame.openPage( new PartiePanel(this.frame, gj) );
	}

	/**
	  * Mise à jour de l'affichage de la partie
	  */
	public void displayTourJoueur (GestionJeu gj)
	{
		// Màj
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

	public Joueur				displaychoixJoueur(String demande, String err, Joueur[] tabJ) {return null;}
	public Etablissement 		displaychoixJoueur(String demande, String err, Etablissement[] tabE) {return null;}

}
