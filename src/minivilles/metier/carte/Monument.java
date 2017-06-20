package minivilles.metier.carte;


public class Monument extends Carte
{
	boolean estConstruit;

	public Monument (String nom, String description, int cout)
	{
		super(nom, "Monument", description, false, cout);

		this.estConstruit = false;
	}

	public void construction () { this.estConstruit = true; }

	/* GETTER */

	public boolean estConstruit () { return this.estConstruit; }
	public String getEtat()
	{
		return this.estConstruit ? "Construit" : "Pas fini ";
	}

	/* SETTER */

	public void setEtat (boolean bool)
	{
		this.estConstruit = bool;
	}

	/* toString */
	
	public String[][] toStringAffichage(String nom)
	{
		switch(nom)
		{
			case "Gare":
				return new String[][]{{" "," "," "," "," "," "," "," "," ","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_"," "," "," "," "," "},
									  {" "," "," "," "," "," "," "," ","/","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","\"," "," "," "," "},
									  {" "," "," "," "," "," "," ","|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","|"," "," "," "},
									  {" "," "," "," "," "," "," ","|"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","|"," "," "," "},
									  {" "," "," ","/","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
									  {" "," ","/","_","_","_","_","_","_","_","_","_","_","_","_","_","_","_","/"," "," "," "," ","/","_","_","_","_","_","_","_","_","_"},
									  {" ","/"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
									  {"(","-","-","-","-","O","O","O","-","-","-","-","-","-","-","-","-","-","-","O","O","O","-","-","-","-","-","-","-","-","-","-","-"},
									  {"=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","=","="}
									 };
			
			case "Centre Commercial":
				return null;
			
			case "Parc d'Attractions":
				return null;
			
			case "Tour Radio":
				return null;
			
		}
	}

	public String toStringNom ()
	{
		return String.format( "%-18.18s", this.getNom() );
	}
}
