package minivilles.metier;

import minivilles.metier.carte.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Map;


public class Joueur
{
	private String 	prenom;
	private int 	monnaie;

	private ArrayList<Etablissement> 	etablissements;
	private ArrayList<Monument> 		monuments;

	/* Constructeur principal */
	public Joueur(String prenom, int monnaie)
	{
		this.prenom = prenom;
		this.monuments 		= new ArrayList<Monument>( Arrays.asList(new Monument[] {	new Monument("Tour Radio", "", 22),
																						new Monument("Gare", "", 4),
																						new Monument("Centre Commercial", "", 10),
																						new Monument("Parc d'Attractions", "", 16) }) );

		this.etablissements = new ArrayList<Etablissement>( Arrays.asList(new Etablissement[] { new EtablissementBleu("Champs de blé", 	"Culture", 	1, 1, 1),
																								new EtablissementVert("Boulangerie", 	"Commerce", 1, 1, 2,3) }) );

		this.monnaie = monnaie;
	}

	public void actionCartes(Joueur joueur, int des)
	{
		for (Etablissement e : this.etablissements) {
			e.action(joueur, des, this);
		}
	}

	public int getNbDes() { return this.monuments.get(0).estConstruit() ? 2 : 1;}

	/* GETTER */

	public String 					getPrenom ()					{ return this.prenom;				}
	public ArrayList<Monument> 		getMonuments ()					{ return this.monuments;			}
	public ArrayList<Etablissement> getEtablissements ()			{ return this.etablissements;		}
	public int 						getMonnaie ()					{ return this.monnaie;				}

	/* SETTER */

	public boolean addMonnaie (int valeur)
	{
		if (this.monnaie + valeur >= 0) {
			this.monnaie += valeur;
			return true;
		}
		return false;
	}

	public void addEtablissement (Etablissement et)			{ this.etablissements.add(et);	}

	/* toString */

	public String toString ()
	{
		return String.format( "%-20s -> %4d", this.prenom, this.monnaie );
	}

	public String toStringCartes ()
	{
		//return String.format( "%s", this.etablissements.stream().map(Etablissement::toStringNom).collect(Collectors.joining(" | ")) );
		String sRet = "\t";

		ArrayList<Etablissement> arTemp = new ArrayList<Etablissement>(etablissements);

		int i=0;
		while ( arTemp.size() !=0 )
		{
			int nb=0;
			Carte cTemp = arTemp.get(0);

			int j=0;
			while ( j < arTemp.size() )
			{
				if (arTemp.get(j).equals(cTemp))
				{
					arTemp.remove(j);
					nb++;
				}
				else
					j++;
			}

			sRet += String.format( "%s (x%d)", cTemp.toStringNom(), nb);

			if (arTemp.size() != 0)
			{
				if (i%4 == 3)	sRet += "\n\t";
				else			sRet += " | ";
			}
			i++;
		}

		return sRet;
	}
}
