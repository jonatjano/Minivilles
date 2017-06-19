package minivilles.metier;


public abstract class Etablissement extends Carte
{
	public Etablissement (String nom, String type, String description, int nbMax, int... cout)
	{
		super(nom, type, description, nbMax, cout);
	}


	public String toString ()
	{
		String sRet = "";

		sRet += "";

		return super.toString() + sRet;
	}
}
