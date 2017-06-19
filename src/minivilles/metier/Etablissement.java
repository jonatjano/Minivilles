package minivilles.metier;


public abstract class Carte
{
	public Carte (String nom, String type, String description, int cout, int nbMax)
	{
		this.nom 			= nom;
		this.type 			= type;
		this.description 	= description;
		this.cout			= cout;
		this.nbMax			= nbMax;
	}


	/* GETTER */

	public int getNbMax ()
	{
		return this.nbMax;
	}


	public String toString ()
	{
		String sRet = "";

		sRet += "";

		return super.toString() + sRet;
	}
}
