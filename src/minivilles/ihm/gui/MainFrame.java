package minivilles.ihm.gui;

import minivilles.Controleur;
import java.awt.*;
import javax.swing.*;


public class MainFrame extends JFrame
{
	private Controleur controler;


	public MainFrame (Controleur controler)
	{
		super();
		// try
		// {
		// 	UIManager.setLookAndFeel( new SyntheticaBlackEyeLookAndFeel() );
		// } catch (Exception e) {}
		this.controler = controler;

		this.setTitle("Jeu cool");
		this.setSize(1200,600);
        this.setLocation(200,150);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Controleur getControler ()
	{
		return this.controler;
	}

	public void openPage (JPanel panel)
	{
		this.getContentPane().removeAll();
		this.add( panel );

		this.validate();
  		this.repaint();
		this.setVisible(true);
	}
}


