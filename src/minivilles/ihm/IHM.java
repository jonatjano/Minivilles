package minivilles.ihm;

import minivilles.Controleur;
import minivilles.metier.*;
import minivilles.metier.carte.*;
import java.util.ArrayList;


public abstract class Ihm
{
	protected Controleur controler;

	public abstract String 				displayMenu ();
	public abstract ArrayList<String> 	displayChoixJoueurs ();
	public abstract String 				displayNouveauTour (Pioche pioche, Joueur[] tabJ, int numTour);
	public abstract int[] 				displayTourJoueur (int numTour, int indexFirstPlayer, Pioche pioche, Joueur[] tabJ, Joueur joueurActuel);
	public abstract boolean 			displayFinPartie (Joueur j, int nbTour);
	public abstract int 				displayChoixDe (int min, int max);
}
