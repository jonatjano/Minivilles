package minivilles.metier;


public class Pioche
{
	Carte[] cartes;
	int[] 	nbCartes;

	/* Constructeur */

	public Pioche ()
	{
		int nb = 12;
		cartes 		= new Carte[nb];	// 12 (+ 3 (Les monuments))
		nbCartes	= new int[nb];
	}

	/* toString */

	public String toString ()
	{
		String sRet = "";

		for (int i = 0; i < this.cartes.length; i++)
			sRet += String.format( "x %d\n%s", this.nbCartes[i], this.cartes[i].toString() );

		return sRet;
	}
}
