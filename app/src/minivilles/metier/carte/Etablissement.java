package minivilles.metier.carte;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import minivilles.metier.Joueur;

public abstract class Etablissement extends Carte
{
	public static final Dimension DIM_ET = new Dimension(185, 273);
	private ArrayList<Integer> 	valActivation;


	public Etablissement (String nom, String type, String description, boolean estLimite, int coutPiece, int lig, int col, int... valActivation)
	{
		super(nom, type, description, estLimite, coutPiece, lig, col);

		this.valActivation	= new ArrayList<Integer>();
		for (int i = 0; i < valActivation.length; i++)
		    this.valActivation.add(valActivation[i]);
	}

	public abstract void 	action(Joueur j, int des, Joueur possesseur);
	public abstract String 	getColor();
	public 			boolean canActivate(int des) 	{ for (Integer i : valActivation) {if (i == des) {	return true;}} return false; }
}
