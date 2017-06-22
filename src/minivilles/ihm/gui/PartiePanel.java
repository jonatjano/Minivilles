package minivilles.ihm.gui;

import minivilles.metier.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class PartiePanel extends JPanel implements ActionListener
{
	private MainFrame 	frame;
	private JButton 	quittGameB;

	private Joueur[]	tabJ;


	public PartiePanel (MainFrame frame, Joueur[] tabJ)
	{
		this.frame 	= frame;
		this.tabJ	= tabJ;
		this.frame.setLayout( new GridLayout() );

		/* Panel central */

		JPanel centerP = new JPanel();
		centerP.setBackground(Color.WHITE);
		centerP.setPreferredSize( new Dimension(150, 150) );
		Graphics g = centerP.getGraphics();
		centerP.repaint();
		System.out.println(g);

		double 	x = (centerP.getPreferredSize().getWidth() / 2) / 2,
				y = (centerP.getPreferredSize().getHeight() / 2) / 2;
		for (Joueur j : tabJ)
		{
			System.out.println(j);
			JLabel lab = new JLabel( j.getPrenom() );
			
			g.drawString( 	lab.getText(),
							(int) (x - lab.getPreferredSize().getWidth()),
							(int) (y - lab.getPreferredSize().getHeight()) );

			centerP.add(lab);

			// x += 10;
			// y -= 15;
			lab.repaint();
		}
		this.add( centerP );


		/* Panel du bas */

		JPanel bottomPanel = new JPanel();
		this.add(bottomPanel);

		this.quittGameB 	= new JButton("Quitter la partie");
		this.quittGameB.addActionListener( this );
		this.add( quittGameB );

        this.setVisible(true);
	}

	public void actionPerformed (ActionEvent e)
	{
		if ( e.getSource() == this.quittGameB )
			this.frame.openPage( new MainMenu(frame) );
	}
}


