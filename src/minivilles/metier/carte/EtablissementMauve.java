package minivilles.metier.carte;

import minivilles.metier.Joueur;

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
	
	
	public  EtablissementMauve (String nom, String type, int puissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format(EtablissementMauve.DESC_MAUVE[EtablissementMauve.getIndice(nom)],puissance == 0 ? "" : String.valueOf(puissance)), true, coutPiece, valActivation);
		this.puissance = puissance;
	}
	
	
	public void action(Joueur j, int des, Joueur possesseur) {}

	public void action(Joueur j, int des, Joueur possesseur, Joueur[] tabJoueur)
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
					
					break;
					
				case 2:
					
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
