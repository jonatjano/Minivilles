package miniVilles.metier;


public class Pioche
{
	Carte[] cartes;
	int[] 	nbCartes;


	public Pioche ()
	{
		cartes 		= new Carte[12];	// 12 (+ 3)
		nbCartes	= new int[];
	}

	public String toString ()
	{
		String sRet = "";

		for (Carte carte : cartes)
			sRet += carte.toString();

		return sRet;
	}
}