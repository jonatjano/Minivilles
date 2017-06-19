package minivilles.metier.carte;


public class EtablissementVert extends Etablissement
{
	public EtablissementVert (String nom, String type, int puissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s de la banque", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, valActivation);
	}

	public EtablissementVert (String nom, String type, int puissance, String typePuissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s de la banque pour chaque établissement de type %s que vous possédez", puissance, (puissance == 1) ? "" : "s", typePuissance), false, coutPiece, valActivation);
	}
}
