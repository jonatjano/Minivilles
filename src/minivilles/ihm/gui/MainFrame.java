package minivilles.ihm.gui;

import java.awt.*;
import javax.swing.*;


public class MainFrame extends JFrame
{
	public MainFrame ()
	{
		super();
		// try
		// {
		// 	UIManager.setLookAndFeel( new SyntheticaBlackEyeLookAndFeel() );
		// } catch (Exception e) {}
		
		this.setTitle("Jeu cool");
		this.setSize(1200,600);
        this.setLocation(200,150);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void openPage (JPanel panel)
	{
		this.getContentPane().removeAll();
		this.add( panel );

		this.setVisible(true);
	}
}


