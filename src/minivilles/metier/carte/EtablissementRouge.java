package minivilles.metier.carte;

import minivilles.metier.Joueur;

public class EtablissementRouge extends Etablissement
{
	private int puissance;

	public EtablissementRouge (String nom, String type, int puissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s du joueur qui a lancé les dés", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, valActivation);
		this.puissance = puissance;
	}

	public void action(Joueur j, int des, Joueur possesseur)
	{
		int pow = this.puissance;

		if ( ( this.type.equals("Restauration") || this.type.equals("Commerce") ) && possesseur.hasCentreComm() ) { pow++; }

		if (canActivate(des)) {
			System.out.println("activation de " + getNom());
			int mon = j.getMonnaie();
			if (mon >= pow) {
				j.addMonnaie(- pow);
				possesseur.addMonnaie(pow);
			}
			else {
				j.addMonnaie(- mon);
				possesseur.addMonnaie(mon);
			}
		}
	}
}
