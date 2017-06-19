package minivilles.metier;

import minivilles.metier.carte.*;


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
		for (int i = 0; i < this.nbCartes.length; i++)
			this.nbCartes[i] = 6;


		// Initialisation des cartes
		this.cartes[0] 	= new EtablissementBleu("Champs de blé", 				"Culture", 		1, 					1, 1);
		this.cartes[1]	= new EtablissementBleu("Ferme", 						"Elevage", 		1, 					1, 2);
		this.cartes[5] 	= new EtablissementBleu("Forêt", 						"Ressources", 	1, 					3, 5);
		this.cartes[8] 	= new EtablissementBleu("Mine", 							"Ressources", 	5, 					6, 9);
		this.cartes[10] 	= new EtablissementBleu("Verger", 						"Culture", 		3, 					3, 10);

		this.cartes[2] 	= new EtablissementVert("Boulangerie", 					"Commerce", 	1, 					1, 2,3);
		this.cartes[4] 	= new EtablissementVert("Superette", 					"Commerce", 	3, 					2, 4);
		this.cartes[6] 	= new EtablissementVert("Fromagerie", 					"Usine", 		3, "Elevage", 		5, 7);
		this.cartes[7] 	= new EtablissementVert("Fabrique de Meubles", 			"Usine", 		3, "Ressources", 	3, 8);
		this.cartes[11] = new EtablissementVert("Marché de fruits et légumes", 	"Marché", 		2, "Culture", 		2, 11,12);

		this.cartes[3] 	= new EtablissementRouge("Café", 						"Restauration", 1, 					2, 3);
		this.cartes[9] 	= new EtablissementRouge("Restaurant", 					"Restauration", 2, 					3, 9,10);
	}

	/* toString */

	public String toString ()
	{
		String sRet = "";

		for (int i = 0; i < this.cartes.length; i++)
			sRet += String.format( "x %d\n%s\n", this.nbCartes[i], this.cartes[i].toString() );

		return sRet;
	}

	public String toStringNom ()
	{
		String sRet = "";

		for (int i = 0; i < this.cartes.length; i++)
		{
			sRet += String.format( "%s - x %d", this.cartes[i].toStringNom(), this.nbCartes[i] );

			if (i%3 == 2)	sRet += "\n";
			else 			sRet += " | ";
		}

		return sRet;
	}
}
