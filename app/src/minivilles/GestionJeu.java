package minivilles;

import minivilles.ihm.Ihm;
import minivilles.ihm.*;
import minivilles.metier.*;
import minivilles.util.Utility;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;


public class GestionJeu
{
	private static final int BEG_MON = 3;

	private Ihm 		ihm;
	private Pioche 		pioche;
	private int 		nbJoueur,
						indexJoueurActuel,
						indexFirstPlayer,
						numTour;
	private Joueur[] 	tabJoueur;
	private Joueur 		joueurGagnant;
	private Joueur 		joueurActuel;
	private boolean		isEvaluation;


	public GestionJeu (Ihm ihm, ArrayList<String> names)
	{
		this.ihm 				= ihm;
		this.pioche 			= new Pioche();
		this.nbJoueur 			= names.size();
		this.isEvaluation		= false;
		this.indexFirstPlayer	= 0;
		this.indexJoueurActuel 	= this.indexFirstPlayer;
		this.numTour			= 1;

		this.tabJoueur = new Joueur[this.nbJoueur];
		for (int i = 0; i < nbJoueur; i++)
			tabJoueur[i] = new Joueur(names.get(i), GestionJeu.BEG_MON);

		this.joueurActuel 	= null;
		this.joueurGagnant	= null;
	}
	
	public GestionJeu (Ihm ihm, String file, boolean ev)
	{
		this.ihm 				= ihm;
		this.pioche 			= new Pioche();
		try
		{
			String chemin = Controleur.PATH + "/PartieInit/" + file;
			if ( !ev )
				chemin = Controleur.PATH + "/Sauvegarde/" + file;
			Scanner sc = new Scanner(new File(chemin));
		
			String ligneJoueur  	= sc.nextLine();
			String[] infoPartie 	= sc.nextLine().split(";");
			this.numTour	    	= Integer.parseInt(infoPartie[0]);
			this.indexFirstPlayer	= Integer.parseInt(infoPartie[1]);
			this.indexJoueurActuel	= Integer.parseInt(infoPartie[2]);
			this.isEvaluation	= infoPartie[3].equals("V");
			
			String[] tabNomJoueur = ligneJoueur.split(";");
			this.nbJoueur = tabNomJoueur.length;
			this.tabJoueur = new Joueur[this.nbJoueur];
			
			String StringInfoJ = sc.nextLine();
			for (int cptNom=0; cptNom < this.nbJoueur ; cptNom++)
			{
				String[] tabInfoJ = StringInfoJ.split(";");
				int piece = Integer.parseInt(tabInfoJ[0]);
				String[] tabCarteJ = tabInfoJ[1].split(":");
				
				this.tabJoueur[cptNom] = new Joueur(tabNomJoueur[cptNom],10000);
				this.tabJoueur[cptNom].removeEtablissement (this.tabJoueur[cptNom].getEtablissements().get(0));	
				this.tabJoueur[cptNom].removeEtablissement (this.tabJoueur[cptNom].getEtablissements().get(0));
				
				for (int i=0; i < tabCarteJ.length; i++)
					for (int j=0; j < Integer.parseInt(tabCarteJ[i]) ; j++)
						this.tabJoueur[cptNom].addEtablissement(this.pioche.getCartes()[i]);
				
				String[] tabMonuJ = tabInfoJ[2].split(":");
				for (int i=0 ; i < tabMonuJ.length ; i++)
					if ( tabMonuJ[i].equals("V") )
						this.tabJoueur[cptNom].construireMonument(i);
				
				this.tabJoueur[cptNom].setMonnaie (piece);
				try{StringInfoJ = sc.nextLine();} catch(Exception e) {}
			}
		} catch(Exception e){}
		

		this.joueurActuel 	= null;
		this.joueurGagnant	= null;
	}

	public void lancer ()
	{
		this.joueurActuel		= this.tabJoueur[ this.indexJoueurActuel ];

		this.ihm.displayDebutPartie( this );
		this.ihm.displayTourJoueur( this );
	}
	
	public void save (String file)
	{
		try
		{
			
			PrintWriter pw = new PrintWriter( new File(Controleur.PATH + "/Sauvegarde/" + file));
			
			String nom = tabJoueur[0].getPrenom();
			for (int i =1; i < tabJoueur.length ; i++)
			{
				nom += ";" + tabJoueur[i].getPrenom();
			}
			pw.println(nom);
			pw.println(this.numTour + ";" + this.indexFirstPlayer + ";" + this.indexJoueurActuel + ";" + (this.isEvaluation == true ? "V" : "F" ));
			
			for(Joueur j : tabJoueur)
			{
				String sInfoJ = j.getMonnaie() + ";";
				
				sInfoJ += j.getnbEtablissements(pioche.getCartes()[0]);
				for (int i=1 ; i < pioche.getCartes().length ; i++)
					sInfoJ += ":" + j.getnbEtablissements(pioche.getCartes()[i]);
				
				sInfoJ += ";" + ( j.hasGare() 		? "V" : "F" );
				sInfoJ += ":" + ( j.hasCentreComm() ? "V" : "F" );
				sInfoJ += ":" + ( j.hasParc() 		? "V" : "F" );
				sInfoJ += ":" + ( j.hasTourRadio() 	? "V" : "F" );
				
				pw.println(sInfoJ);
			}
			pw.close();
		} catch(Exception e) {}
	}

	public void resultatTour (int[] valDe)
	{
		boolean aUnDouble = this.hasPlayerDouble( valDe );

		if ( !aUnDouble )
			this.indexJoueurActuel++;
		this.indexJoueurActuel = this.indexJoueurActuel%nbJoueur;

		// Nouveau tour
		if (this.indexJoueurActuel == indexFirstPlayer && !aUnDouble)
			this.numTour++;

		this.joueurActuel = this.tabJoueur[ this.indexJoueurActuel ];



		// Vérifie si un joueur a gagné
		for (Joueur j : tabJoueur)
			if ( j.aGagne() )
				this.joueurGagnant = j;

		if ( this.joueurGagnant == null)
			this.ihm.displayTourJoueur( this );
		else
			// Affiche la cérémonie du grand vainqueur
			this.ihm.displayFinPartie(this.joueurGagnant, this.numTour);
	}

	public void activateCardsAction (int valDeTotale)
	{
		// Une fois que le lancer est définitif...
		// Active les actions pour les joueurs en commençant par le premier et en allant dans le sens inverse des aiguilles d'une montre
		for (int i = 0; i < this.tabJoueur.length; i++)
			this.tabJoueur[ Utility.posModulo(this.indexJoueurActuel - i - 1, this.tabJoueur.length) ].actionCartes( this.joueurActuel, valDeTotale, this.tabJoueur, this.ihm);
	}


	public boolean 	hasPlayerDouble (int[] valDe)	{ return valDe.length == 2 && this.joueurActuel.hasParc() && valDe[0] == valDe[1];}
	public Pioche 	getPioche ()					{ return this.pioche; 			}
	public int 		getIndexFirstPlayer ()			{ return this.indexFirstPlayer; }
	public int 		getNumTour ()					{ return this.numTour; 			}
	public Joueur[] getTabJoueur ()					{ return this.tabJoueur; 		}
	public Joueur 	getJoueurActuel ()				{ return this.joueurActuel; 	}
	public boolean 	isEvaluationMode ()				{ return this.isEvaluation; 	}

	public int calcIndexCourant ()
	{
		int indexJ = 0;
		for ( int i = 0; i < this.tabJoueur.length; i++)
			if ( this.tabJoueur[i] == this.joueurActuel )
				indexJ = i;

		return indexJ;
	}
}
