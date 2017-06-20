package minivilles.metier.carte;

import minivilles.metier.Joueur;

public class EtablissementBleu extends Etablissement
{
	private int puissance;

	public EtablissementBleu (String nom, String type, int puissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s de la banque", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, valActivation);
		this.puissance = puissance;
	}

	public void action(Joueur j, int des, Joueur possesseur)
	{
		int pow = this.puissance;

		if ( ( this.type.equals("Restauration") || this.type.equals("Commerce") ) && possesseur.hasCentreComm() ) { pow++; }

		if (canActivate(des)) {
			possesseur.addMonnaie(pow);
		}
	}
}
