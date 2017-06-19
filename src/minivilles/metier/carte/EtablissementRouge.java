package minivilles.metier.carte;


public class EtablissementRouge extends Etablissement
{
	public EtablissementRouge (String nom, String type, int puissance, int coutPiece, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s du joueur qui a lancé les dés", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, valActivation);
	}

	public void action(Joueur j, int des, Joueur possesseur)
	{
		if (canActivate(des)) {
			int mon = j.getMonnaie();
			if (mon >= puissance) {
				j.addMonnaie(- puissance);
				possesseur.addMonnaie(puissance);
			}
			else {
				j.addMonnaie(- mon);
				possesseur.addMonnaie(mon);
			}
		}
	}
}
