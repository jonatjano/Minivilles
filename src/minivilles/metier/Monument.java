package minivilles.metier;


public class Monument extends Carte
{
	boolean estConstruit;

	/* Constructeur */

	public Monument (String nom, String description, int cout)
	{
		super(nom, "Monument", description, cout, 1);

		this.estConstruit = false;
	}

	/* toString */

	public String toString ()
	{
		String sRet = "";

		sRet += "";

		return super.toString() + sRet;
	}
}
