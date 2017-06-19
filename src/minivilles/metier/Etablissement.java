package minivilles.metier;


public abstract class Etablissement extends Carte
{
	public Etablissement (String nom, String type, String description, int cout, int nbMax)
	{
		super(nom, type, description, cout, nbMax);
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
