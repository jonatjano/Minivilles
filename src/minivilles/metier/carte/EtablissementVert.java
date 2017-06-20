package minivilles.metier.carte;

import minivilles.metier.Joueur;

public class EtablissementVert extends Etablissement
{
	private int puissance;
	private String typePuissance;

	public EtablissementVert (String nom, String type, int puissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s de la banque", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, valActivation);
		this.puissance = puissance;
		this.typePuissance = null;
	}

	public EtablissementVert (String nom, String type, int puissance, String typePuissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s de la banque pour chaque établissement de type %s que vous possédez", puissance, (puissance == 1) ? "" : "s", typePuissance), false, coutPiece, valActivation);
		this.typePuissance = typePuissance;
	}

	public void action(Joueur j, int des, Joueur possesseur)
	{
		if (canActivate(des)) {
			if (typePuissance == null) {
				possesseur.addMonnaie(puissance);
			}
			else {
				ArrayList<Etablissement> etabs = possesseur.getEtablissements();
				int nbType = 0;
				for ( Etablissement e : etabs )) { if (e.getType().equals( typePuissance )) { nbType++; } }
				possesseur.addMonnaie(puissance * nbType);
			}
		}
	}
}
