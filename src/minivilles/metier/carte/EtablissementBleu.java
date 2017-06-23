package minivilles.metier.carte;

import minivilles.metier.Joueur;

public class EtablissementBleu extends Etablissement
{
	private int puissance;

	public EtablissementBleu (String nom, String type, int puissance, int coutPiece, int col, int lig, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s de la banque", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, col, lig, valActivation);
		this.puissance = puissance;
	}

	public void action(Joueur j, int des, Joueur possesseur)
	{
		int pow = this.puissance;

		if ( ( this.getType().equals("Restauration") || this.getType().equals("Commerce") ) && possesseur.hasCentreComm() ) { pow++; }

		if (canActivate(des)) {
			possesseur.addMonnaie(pow);
		}
	}

	public String getColor() { return "Bleu"; }
}
