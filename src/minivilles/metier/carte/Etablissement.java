package minivilles.metier.carte;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import minivilles.metier.Joueur;

public abstract class Etablissement extends Carte
{
	private ArrayList<Integer> 	valActivation;
	private int col,
				lig;


	public Etablissement (String nom, String type, String description, boolean estLimite, int coutPiece, int col, int lig, int... valActivation)
	{
		super(nom, type, description, estLimite, coutPiece);

		this.col = col;
		this.lig = lig;
		
		this.valActivation	= new ArrayList<Integer>();
		for (int i = 0; i < valActivation.length; i++)
		    this.valActivation.add(valActivation[i]);
	}

	public abstract void 	action(Joueur j, int des, Joueur possesseur);
	public abstract String 	getColor();
	public 			boolean canActivate(int des) { for (Integer i : valActivation) {if (i == des) {	return true;}} return false; }
}
