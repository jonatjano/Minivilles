package minivilles.metier.carte;
import java.util.ArrayList;

import minivilles.metier.Joueur;

public class EtablissementVert extends Etablissement
{
	private int puissance;
	private String typePuissance;


	public EtablissementVert (String nom, String type, int puissance, int coutPiece, int col, int lig, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s de la banque", puissance, (puissance == 1) ? "" : "s"), false, coutPiece, col, lig, valActivation);
		this.puissance = puissance;
		this.typePuissance = null;
	}

	public EtablissementVert (String nom, String type, int puissance, String typePuissance, int coutPiece, int col, int lig, int... valActivation)
	{
		super(nom, type, String.format("Recevez %d pièce%s de la banque pour chaque établissement de type %s que vous possédez", puissance, (puissance == 1) ? "" : "s", typePuissance), false, coutPiece, col, lig, valActivation);
		this.typePuissance = typePuissance;
	}

	public void action(Joueur j, int des, Joueur possesseur)
	{
		int pow = this.puissance;

		if ( ( this.getType().equals("Restauration") || this.getType().equals("Commerce") ) && possesseur.hasCentreComm() ) { pow++; }

		if (canActivate(des) && j == possesseur) {
			if (typePuissance == null) {
				possesseur.addMonnaie(pow);
			}
			else {
				ArrayList<Etablissement> etabs = possesseur.getEtablissements();
				int nbType = 0;
				for ( Etablissement e : etabs ) { if (e.getType().equals( typePuissance )) { nbType++; } }
				possesseur.addMonnaie(pow * nbType);
			}
		}
	}

	public String getColor() { return "Vert"; }
}
