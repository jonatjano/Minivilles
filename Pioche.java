package miniVilles.metier;


public class Pioche
{
	Carte[] cartes;
	int[] 	nbCartes;


	public Pioche ()
	{
		cartes 		= new Carte[];
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