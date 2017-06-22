package minivilles.metier;

import minivilles.metier.carte.*;
import minivilles.ihm.Ihm;
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
		this.monuments 		= new ArrayList<Monument>( Arrays.asList(new Monument[] {	new Monument("Gare", "", 4),
																						new Monument("Centre Commercial", "", 10),
																						new Monument("Parc d'Attractions", "", 16),
																						new Monument("Tour Radio", "", 22) }) );

		this.etablissements = new ArrayList<Etablissement>( Arrays.asList(new Etablissement[] { new EtablissementBleu("Champs de blé", 	"Culture", 	1, 1, 1),
																								new EtablissementVert("Boulangerie", 	"Commerce", 1, 1, 2,3) }) );

		this.monnaie = monnaie;
	}

	public void actionCartes (Joueur joueur, int des, Joueur[] tabJ, Ihm ihm)
	{
		// activation des etablissements rouges
		for (Etablissement e : this.etablissements) {
			if ( e.getColor().equals("Rouge") ) {
				e.action(joueur, des, this);
			}
		}

		for (Etablissement e : this.etablissements) {
			if ( e.getColor().equals("Mauve") ) {
				((EtablissementMauve)e).action(joueur, des, this, tabJ, ihm);
			}
		}

		// activation des autres etablissements
		for (Etablissement e : this.etablissements) {
			if ( !e.getColor().equals("Rouge") && !e.getColor().equals("Mauve")) {
				e.action(joueur, des, this);
			}
		}
	}

	public int getNbDes() {
		for (Monument m : this.monuments)
			if (m.getNom().equals("Gare"))
				return m.estConstruit() ? 2 : 1;
		return 1;
	}

	public boolean hasCentreComm() {
		for (Monument m : this.monuments)
			if (m.getNom().equals("Centre Commercial"))
				return m.estConstruit();
		return false;
	}

	public boolean hasParc() {
		for (Monument m : this.monuments)
			if (m.getNom().equals("Parc d'Attractions"))
				return m.estConstruit();
		return false;
	}

	public boolean hasTourRadio() {
		for (Monument m : this.monuments)
			if (m.getNom().equals("Tour Radio"))
				return m.estConstruit();
		return false;
	}

	public boolean hasChaineDeTelevision() {
		for (Etablissement e : this.etablissements)
			if (e.getNom().equals("Chaîne de télévision"))
				return true;
		return false;
	}

	public boolean hasCentreAffaire() {
		for (Etablissement e : this.etablissements)
			if (e.getNom().equals("Centre d'affaires"))
				return true;
		return false;
	}

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

	public Monument construireMonument (int id)
	throws IndexOutOfBoundsException
	{
		Monument m = this.monuments.get(id);

		if ( m != null && !m.estConstruit() && this.addMonnaie( -m.getCoutPiece() ))
		{
			m.construction();
			return m;
		}

		return null;
	}

	public boolean aGagne()
	{
		return 	this.monuments.get(0).estConstruit() &&
				this.monuments.get(1).estConstruit() &&
				this.monuments.get(2).estConstruit() &&
				this.monuments.get(3).estConstruit();
	}

	/* toString */

	public String toString ()
	{
		return String.format( "%-20s -> %4d", this.prenom, this.monnaie );
	}

	public String toStringCartes ()
	{
		//return String.format( "%s", this.etablissements.stream().map(Etablissement::toStringNom).collect(Collectors.joining(" | ")) );
		String sRet = "\t~ ETABLISSEMENTS\n\t";


		/* AFICHAGE DES ETABLISSEMENTS */
		ArrayList<Etablissement> arTemp = new ArrayList<Etablissement>(etablissements);
		int i = 0;
		while ( arTemp.size() !=0 )
		{
			int nb = 0;
			Carte cTemp = arTemp.get(0);

			int j = 0;
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

		/* AFFICHAGE DES MONUMENTS */
		sRet += "\n";
		sRet += this.toStringMonuments();


		return sRet;
	}

	public String toStringMonuments ()
	{
		String 	sRet 	= "\t~ MONUMENTS\n\t";

		for (int k = 0; k < this.monuments.size(); k++)
		{
			sRet += String.format( "%s (%s)", this.monuments.get(k).toStringNom(), this.monuments.get(k).getEtat() );

			if (k != this.monuments.size() - 1)
			{
				if (k%2 == 1)	sRet += "\n\t";
				else			sRet += " | ";
			}
		}

		return sRet;
	}

	public String toStringMonumentsNonAchetes ()
	{
		String 	sRet 	= "\t~ MONUMENTS\n\t";

		int k = 0;
		for (int i = 0; i < this.monuments.size(); i++)
		{
			if ( !this.monuments.get(i).estConstruit() )
			{
				sRet += String.format( "%2d ~ %s (%2dP)", (i+1), this.monuments.get(i).toStringNom(), this.monuments.get(i).getCoutPiece() );

				if (k != this.monuments.size() - 1)
				{
					if (k%2 == 1)	sRet += "\n\t";
					else			sRet += " | ";
				}
				k++;
			}
		}

		return sRet;
	}
}
