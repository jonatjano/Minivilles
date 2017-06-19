package minivilles.metier;


public class Monument extends Carte
{
	boolean estConstruit;


	public Monument (String nom, String description, int cout)
	{
		super(nom, "Monument", description, cout, 1);

		this.estConstruit = false;
	}

	public String toString ()
	{
		String sRet = "";

		sRet += "";

		return super.toString() + sRet;
	}
}
