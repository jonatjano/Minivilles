package minivilles.ihm;


public abstract abstract class IHM
{
	protected Controleur controler;

	public abstract String 				displayMenu ();
	public abstract ArrayList<String> 	displayChoixJoueurs ();
	public abstract String 				displayNouveauTour (Pioche pioche, Joueur[] tabJ, int numTour);
	public abstract int[] 				displayTourJoueur (int numTour, int indexFirstPlayer, Pioche pioche, Joueur[] tabJ, Joueur joueurActuel);
	public abstract boolean 			displayFinPartie (Joueur j, int nbTour);
	public abstract abstract int 		displayChoixDe (int min, int max);
}
