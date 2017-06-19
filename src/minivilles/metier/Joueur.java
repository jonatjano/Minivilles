package minivilles.metier;

import minivilles.metier.carte.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Map;


public class Joueur
{
	private String 				prenom;
	private int 				monnaie;

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

	/* GETTER */

	public String 					getPrenom ()					{return this.prenom;				}
	public ArrayList<Monument> 		getMonuments ()					{return this.monuments;				}
	public ArrayList<Etablissement> getEtablissements ()			{return this.etablissements;		}
	public int 						getMonnaie ()					{return this.monnaie;				}

	/* SETTER */

	public void setMonnaie (int valeur)						{ this.monnaie += valeur;					}
	//public void addEtablissement (String nom)	{ this.etablissements.add(etablissement);	}

	/* toString */

	public String toString ()
	{
		return String.format( "%-20s -> %4d", this.prenom, this.monnaie );
	}

	public String toStringCartes ()
	{
		return String.format( "%s", this.etablissements.stream().map(Etablissement::toStringNom).collect(Collectors.joining(" | ")) );
	}
}
