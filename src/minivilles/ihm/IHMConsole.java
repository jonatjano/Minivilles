package minivilles.ihm;

import minivilles.metier.*;
import minivilles.*;


public class IHMConsole
{
	public IHMConsole ()
	{
		
	}

	public void displayTour (int numTour)
	{
		System.out.println( String.format("\n\n\t--- Tour n°%2d ---\n", numTour) );
	}
}
