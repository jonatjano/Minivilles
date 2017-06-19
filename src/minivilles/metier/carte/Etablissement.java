package minivilles.metier.carte;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;


public abstract class Etablissement extends Carte
{
	private ArrayList<Integer> 	valActivation;


	public Etablissement (String nom, String type, String description, boolean estLimite, int coutPiece, int... valActivation)
	{
		super(nom, type, description, estLimite, coutPiece);

		this.valActivation	= new ArrayList<Integer>();
		for (int i = 0; i < valActivation.length; i++)
		    this.valActivation.add(valActivation[i]);
	}

	public ArrayList<Integer> 	getvalActivation ()	{ return this.valActivation;		}

	public String toString ()
	{
		String sRet = "";

		sRet += String.format( "--- %-15s ---\nType : %s\nCoûts : %s\nDescription :\n%s", 	this.getNom(),
																							this.valActivation.stream().map(Object::toString).collect(Collectors.joining(", ")),
																							this.getType(),
																							this.getDescription() );

		return sRet;
	}
}
