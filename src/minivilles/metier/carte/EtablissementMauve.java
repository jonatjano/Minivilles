package minivilles.metier.carte;

import minivilles.metier.Joueur;
import minivilles.ihm.Ihm;
import java.util.ArrayList;


public class EtablissementMauve extends Etablissement
{
	private static String[] DESC_MAUVE;

	private int puissance;


	static
	{
		EtablissementMauve.DESC_MAUVE = new String[3];
		EtablissementMauve.DESC_MAUVE[0] = "Recevez %s pièces de la part de chaque autre joueur.";
		EtablissementMauve.DESC_MAUVE[1] = "Recevez %s pièces du joueur de votre choix.";
		EtablissementMauve.DESC_MAUVE[2] = "%sVous pouvez échanger avec le joueur de votre choix un établissement qui ne soit pas de type spécial.";
	}


	public  EtablissementMauve (String nom, String type, int puissance, int coutPiece, int lig, int col, int... valActivation)
	{
		super(nom, type, String.format(EtablissementMauve.DESC_MAUVE[EtablissementMauve.getIndice(nom)],puissance == 0 ? "" : String.valueOf(puissance)), true, coutPiece, lig, col, valActivation);
		this.puissance = puissance;
	}


	public void action(Joueur j, int des, Joueur possesseur) {}

	public void action(Joueur j, int des, Joueur possesseur, Joueur[] tabJoueur, Ihm ihm)
	{

		if (canActivate(des) && j == possesseur)
		{
			System.out.println("activation de " + getNom());

			switch (EtablissementMauve.getIndice(getNom()))
			{
				case 0:
					for (Joueur jTemp : tabJoueur)
						if ( jTemp != possesseur )
						{
							int nb =this.puissance;
							if (jTemp.getMonnaie() < puissance)
								nb = jTemp.getMonnaie();
							jTemp.addMonnaie(- nb);
							possesseur.addMonnaie(nb);
						}
					break;

				case 1:
					Joueur jEchange;
					String sErr = null;
					do
					{
					do
						{
							jEchange = ihm.displaychoixJoueur("à quel joueur voulez vous voler " + this.puissance + " piece" + ( this.puissance == 1 ? "" : "s" ) + " ?", sErr, tabJoueur);
							if (jEchange == possesseur) sErr = "Vous ne pouvez pas vous voler de l'argent.";
						} while (jEchange == possesseur);
						
						if (jEchange == null) sErr = "Vous devez choisir un joueur !";
					}while (jEchange == null);

					int nb = this.puissance;
					if (jEchange.getMonnaie() < puissance)
						nb = jEchange.getMonnaie();
					jEchange.addMonnaie(- nb);
					possesseur.addMonnaie(nb);
					break;

				case 2:
					sErr = null;
					
					if ( ! ihm.displayDemande("Souhaitez vous échanger un établissement avec un autre joueur ?"))
						return;
					
					do
					{
						jEchange = ihm.displaychoixJoueur("avec quel joueur voulez vous échanger un établissement ?", sErr, tabJoueur);
						if (jEchange == possesseur) sErr = "Vous ne pouvez pas vous échanger un établissement.";
					} while (jEchange == possesseur);
					if (jEchange == null)
						return;
					
					sErr = null;
					
					ArrayList<Etablissement> arTemp = possesseur.getEtablissements();
					for (int i =0; i < arTemp.size() ; i++)
						if ( arTemp.get(i).getColor().equals("Mauve"))
						{
							arTemp.remove(i);
							i--;
						}
						
					Etablissement[] tabEtTemp = arTemp.toArray(new Etablissement[arTemp.size()]); 
					
					Etablissement pEtablissement = ihm.displaychoixJoueur("lequel de vos établissements souhaitez-vous échanger ?", sErr, tabEtTemp);
					if (pEtablissement == null)
						return;
					
					arTemp = jEchange.getEtablissements();
					for (int i =0; i < arTemp.size() ; i++)
						if ( arTemp.get(i).getColor().equals("Mauve"))
						{
							arTemp.remove(i);
							i--;
						}
					
					tabEtTemp = arTemp.toArray(new Etablissement[arTemp.size()]); 
					
					Etablissement jEtablissement = ihm.displaychoixJoueur("lequel des établissements souhaitez-vous prendre ?", sErr, tabEtTemp);
					if (jEtablissement == null)
						return;
					
					possesseur.removeEtablissement(pEtablissement);
					possesseur.addEtablissement(jEtablissement);
					
					jEchange.removeEtablissement(jEtablissement);
					jEchange.addEtablissement(pEtablissement);
					
					break;
			}
		}
	}

	public String getColor() { return "Mauve"; }

	private static int getIndice(String nom)
	{
		int iRet = 0;

		switch (nom)
		{
			case "Stade":
				iRet = 0;
				break;

			case "Chaîne de télévision":
				iRet = 1;
				break;

			case "Centre d'affaires":
				iRet = 2;
				break;
		}
		return iRet;
	}

}
