package minivilles.metier;


public class Pioche
{
	Carte[] cartes;
	int[] 	nbCartes;

	/* Constructeur */

	public Pioche ()
	{
		cartes 		= new Carte[12];	// 12 (+ 3)
		nbCartes	= new int[12];
	}

	/* toString */

	public String toString ()
	{
		String sRet = "";

		for (Carte carte : cartes)
			sRet += carte.toString();

		return sRet;
	}
}
