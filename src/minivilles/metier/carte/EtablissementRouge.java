package minivilles.metier.carte;


public class EtablissementRouge extends Etablissement
{
	public EtablissementRouge (String nom, String type, int puissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s du joueur qui a lancé les dés", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, valActivation);
	}
}
