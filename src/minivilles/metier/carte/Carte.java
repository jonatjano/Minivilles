package minivilles.metier.carte;

import minivilles.metier.Joueur;
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
	private int 				coutPiece;
	private boolean				estLimite;
	private Joueur				joueur;


	public Carte (String nom, String type, String description, boolean estLimite, int coutPiece)
	{
		this.nom 			= nom;
		this.type 			= type;
		this.description 	= description;
		this.estLimite		= estLimite;
		this.coutPiece		= coutPiece;

		this.joueur 		= null;
	}

	/* GETTER */

	public String 				getNom ()			{ return this.nom;			}
	public String 				getType ()			{ return this.type;			}
	public String 				getDescription ()	{ return this.description;	}
	public int 				 	getCout ()			{ return this.coutPiece;	}
	public boolean				estLimite ()		{ return this.estLimite;	}
	public Joueur 				getJoueur ()		{ return this.joueur;		}

	/* SETTER */

	public void		setJoueur (Joueur joueur)		{ this.joueur = joueur;		}

	/* toString */

	public String toString ()
	{
		String sRet = "";

		sRet += String.format( "--- %-15s ---\nType : %s\nCoûts : %s\nDescription :\n%s", 	this.nom,
																							this.coutPiece,
																							this.type,
																							this.description );

		return sRet;
	}

	public String toStringNom ()
	{
		return String.format( "%-17.17s", this.getNom() );
	}
}
