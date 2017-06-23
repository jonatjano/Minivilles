package minivilles.metier.carte;

import minivilles.metier.Joueur;

public class EtablissementRouge extends Etablissement
{
	private int puissance;


	public EtablissementRouge (String nom, String type, int puissance, int coutPiece, int lig, int col, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s du joueur qui a lancé les dés", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, lig, col, valActivation);
		this.puissance = puissance;
	}

	public void action(Joueur j, int des, Joueur possesseur)
	{
		int pow = this.puissance;

		if ( ( this.getType().equals("Restauration") || this.getType().equals("Commerce") ) && possesseur.hasCentreComm() ) { pow++; }

		if (canActivate(des) && j != possesseur) {
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

	public String getColor() { return "Rouge"; }
}
