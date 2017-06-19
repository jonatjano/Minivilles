

public class GestionJeu
{
	Pioche 		pioche;
	Joueur[] 	tabJoueur;
	int 		banque;


	public GestionJeu (int nbJoueur)
	{
		if (this.nbJoueur > 0 && this.nbJoueur <= 4)
		{
			this.pioche = new Pioche();

			this.tabJoueur = new Joueur[nbJoueur];
			for (int i = 0; i < nbJoueur; i++)
				tabJoueur[i] = new Joueur("Michel", 20);	// 3
		}
	}

	public void lancer ()
	{
		
	}


	public static main (String[] args)
	{

	}
}