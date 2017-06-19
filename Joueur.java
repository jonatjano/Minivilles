public class Joueur
{
	String prenom;

	private ArrayList<Carte> etablissements;
	private ArrayList<Carte> monuments;
	private int monnaie;

}

public Joueur(String prenom)
{
	this.prenom = prenom;
	this.monuments = new ArrayList<Carte>();
	this.etablissements = new ArrayList<Carte>();
	this.monnaie = 3;
}

public Joueur(String prenom, int monnaie)
{
	this.prenom = prenom;
	this.monuments = new ArrayList<Carte>();
	this.etablissements = new ArrayList<Carte>();
	this.monnaie = monnaie;
}

//Accesseurs

public String getPrenom(){return this.prenom;}
public ArrayList<Carte> getMonuments(){return this.monuments;}
public ArrayList<Carte> getEtablissements(){return this.etablissements;}
public int getMonnaie(){return this.monnaie;}

//Définisseurs

public void setMonnaie(int valeur){this.monnaie+=valeur;}
public void ajouterMonument(Carte monument){this.monuments.add(monument);}
public void ajouterEtablissement(Carte etablissement){this.monuments.add(etablissement);}
