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

	public String getEtat()
	{
		return this.estConstruit ? "Construit" : "Pas fini ";
	}

	/* toString */

	public String toStringNom ()
	{
		return String.format( "%-18.18s", this.getNom() );
	}
}
