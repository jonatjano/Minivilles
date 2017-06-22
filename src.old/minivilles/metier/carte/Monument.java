package minivilles.metier.carte;


public class Monument extends Carte
{
	private static final String GARE 	=	"_____________________________________    \n"	+
											"  __________/___________________\\______  \n"	+
											"           |                     |       \n"	+
											"           |                     |       \n"	+
											"       /-----------------------------|   \n"	+
											"      /___/  /________/    /________/|   \n"	+
											"     /         _____         ______  |   \n"	+
											"  ==(----OOO-----------OOO-----------)===\n"	+
											"=======================================  \n";
	private static final String CEN_CO	=	"               ________                          \n"	+
											"         _____/       /|_______________          \n"	+
											"       /     /_______/ /              /|         \n"	+
											"      /      |_______|/              / |         \n"	+
											"     /_____________________________ /  |         \n"	+
											"     |_____________________________|   |         \n"	+
											"     |_____________________________|   |         \n"	+
											"     |_____________________________|   |         \n"	+
											"     |_______|             |_______|   |         \n"	+
											"     |_______|   M A L L   |_______|   |         \n"	+
											"     |_______|_____________|_______|   |         \n"	+
											"     |_____________________________|   |         \n"	+
											"     |_____________________________|   |         \n"	+
											"     |_|   `   |_________|  `    |_|   |         \n"	+
											"     |_|    ` `|_________|     ` |_|   |         \n"	+
											"     |_| `  `  |_________|  `    |_|   |         \n"	+
											"     |_|  `  ` |_|  |  |_| `   ` |_|  /      \\__|\n"	+
											"     |_|___`___|_| .|. |_|___`___|_| /   \\__|'o o\n"	+
											"     |___________|__|__|___________|/     o o    \n"	+
											"                                                 \n"	+
											"       \\__|'             \\__|'                   \n"	+
											"        o o  \\__|'        o o                    \n"	+
											"              o o                                 \n";
	private static final String PAR_ATT	=   "                      	__---------------__                          \n"	+
											"                      /                    \\                        \n"	+
											"                  /     \\              /     \\        O             \n"	+
											"             .  /         \\          /         \\     /              \n"	+
											"          .'.:./             \\      /             \\          :      \n"	+
											"          -=:o|               \\    /               |      '.\\'/.*   \n"	+
											"          '.'|                 \\__/                 |     -= o =-   \n"	+
											"         ,  ( ________________ /  \\ ________________ )    .'/.\\'.   \n"	+
											"       ,    ( ________________|    |________________ )       :      \n"	+
											"            (                  \\__/                  )        ,     \n"	+
											"             |                 /  \\                 |          ,    \n"	+
											"     O        |               /    \\               |      o    ,    \n"	+
											"     \\         \\             /  /\\  \\             /       |      ,  \n"	+
											"                 \\          /  /  \\  \\          /                ,  \n"	+
											"                o  \\       /  /____\\  \\       /       O           , \n"	+
											"            O   |     \\__ /___|____|___\\ __/         /              \n"	+
											"         o  |            /|     __     |\\     o                     \n"	+
											" ___ ___/___|___ ___ ___/_|    |. |    |_\\_ ___\\___ ___ ___ ___ ___ \n"	+
											"|___|___|___|___|___|___| |____|__|____|___|___|___|___|___|___|___|\n";
	private static final String TOUR_RA	=   "             (( . ))            \n"	+
											"            ((  |  ))           \n"	+
											"                |               \n"	+
											"               / \\              \n"	+
											"               /|\\              \n"	+
											"              /v v\\             \n"	+
											"              // \\\\             \n"	+
											"             /  v  \\            \n"	+
											"             / / \\ \\            \n"	+
											"            /v \\ / v\\           \n"	+
											"           _/_______\\_          \n"	+
											"          |___________|         \n"	+
											"            //\\   /\\\\           \n"	+
											"           /   \\ /   \\          \n"	+
											"           /   / \\   \\          \n"	+
											"          /v\\ /   \\ /v\\         \n"	+
											"         /  / \\   / \\  \\        \n"	+
											"        /\\ /   \\ /   \\ /\\       \n"	+
											"       /  \\\\   / \\   //  \\      \n"	+
											"     /v\\  /v\\ /v v\\ /v\\  /v\\    \n"	+
											"    / \\/||\\ / \\   / \\ /||\\/ \\   \n";

	boolean estConstruit;


	public Monument (String nom, String description, int cout)
	{
		super(nom, "Monument", description, false, cout);

		this.estConstruit = false;
	}

	public void construction () { this.estConstruit = true; }

	/* GETTER */

	public boolean estConstruit () { return this.estConstruit; }
	public String getEtat()
	{
		return this.estConstruit ? "Construit" : "Pas fini ";
	}

	/* SETTER */

	public void setEtat (boolean bool)
	{
		this.estConstruit = bool;
	}

	/* toString */

	public static String[][] getStringAffichage (String nom)
	{
		String str = "";
		switch (nom)
		{
			case "Gare":				str = Monument.GARE;	break;
			case "Centre Commercial":	str = Monument.CEN_CO;	break;
			case "Parc d'Attractions":	str = Monument.PAR_ATT;	break;
			case "Tour Radio":			str = Monument.TOUR_RA;	break;
		}

		String[] rows = str.split("\n");

		String[][] tab = new String[rows.length][]; 
		int cpt = 0;
		for (String row : rows)
			tab[cpt++] = row.split("");


		return tab;
	}

	public String toStringNom ()
	{
		return String.format( "%-18.18s", this.getNom() );
	}
}
