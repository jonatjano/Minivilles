package minivilles.metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public abstract class Carte
{
	// Moment d'utilisation : Enum ?
	// = Couleur carte
	private String 				nom;
	private String 				type;
	private String 				description;
	private ArrayList<Integer> 	couts;
	private int 				nbMax;
	private Joueur				joueur;


	public Carte (String nom, String type, String description, int nbMax, int... couts)
	{
		this.nom 			= nom;
		this.type 			= type;
		this.description 	= description;
		this.nbMax			= nbMax;
		this.couts			= new ArrayList<Integer>();//Arrays.asList(new Integer[] {new Integer(2)});
		for (int i = 0; i < couts.length; i++)
		    this.couts.add(couts[i]);

		this.joueur 		= null;
	}

	/* GETTER */

	public String 				getNom ()			{ return this.nom;			}
	public String 				getType ()			{ return this.type;			}
	public String 				getDescription ()	{ return this.description;	}
	public ArrayList<Integer> 	getCout()			{ return this.couts;		}
	public int 					getNbMax ()			{ return this.nbMax;		}
	public Joueur 				getJoueur ()		{ return this.joueur;		}

	/* SETTER */

	public void		setJoueur (Joueur joueur)		{ this.joueur = joueur;		}

	/* toString */

	public String toString ()
	{
		String sRet = "";

		sRet += String.format( "--- %-15s ---\nType : %s\nCoûts : %s\nDescription :\n%s", 	this.nom,
																							this.couts.stream().map(Object::toString).collect(Collectors.joining(", ")),
																							this.type,
																							this.description );

		return sRet;
	}
}
