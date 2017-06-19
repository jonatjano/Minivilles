package minivilles.metier.carte;


public class EtablissementBleu extends Etablissement
{
	public EtablissementBleu (String nom, String type, int puissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s de la banque", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, valActivation);
	}
}
