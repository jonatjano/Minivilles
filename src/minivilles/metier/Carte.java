package minivilles.metier;


public class Carte
{
	// Moment d'utilisation : Enum ?
	// = Couleur carte
	String 	nom;
	String 	type;
	String 	description;
	int 	cout;
	int 	nbMax;

	public Carte (String nom, String type, String description, int cout, int nbMax)
	{
		this.nom 			= nom;
		this.type 			= type;
		this.description 	= description;
		this.cout			= cout;
		this.nbMax			= nbMax;
	}

	/* GETTER */

	public String getNom(){return this.nom;}
	public String getType(){return this.type;}
	public String getDescription(){return this.description;}
	public int getCout(){return this.cout;}
	public int getNbMax (){return this.nbMax;}

	/* toString */

	public String toString ()
	{
		String sRet = "";

		sRet += cout + " " + type + " " + nom + " " + description;

		return sRet;
	}
}
