package minivilles.metier.carte;


public class Monument extends Carte
{
	boolean estConstruit;

	/* Constructeur */

	public Monument (String nom, String description, int cout)
	{
		super(nom, "Monument", description, false, cout);

		this.estConstruit = false;
	}

	public boolean estConstruit() { return this.estConstruit; }

	/* toString */

	public String toString ()
	{
		String sRet = "";

		sRet += "";

		return super.toString() + sRet;
	}
}
