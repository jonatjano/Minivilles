package minivilles.ihm.gui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class ChoixJoueursLine extends JPanel implements ActionListener
{
	private ChoixJoueursMenu	parent;
	private JLabel				indexL;
	private JButton 			addB;
	private JTextField			nameTF;


	public ChoixJoueursLine (ChoixJoueursMenu parent, int index)
	{
		this.parent = parent;
		this.setLayout( new GridLayout(1, 3) );


		this.indexL = new JLabel("J" + index + " : ");
		this.add( indexL );

		this.nameTF = new JTextField();
		this.nameTF.setEnabled(false);
		this.add( nameTF );

		this.addB 	= new JButton("Ajouter");
		this.addB.addActionListener( this );
		this.add( addB );


        this.setVisible(true);
        this.addB.setVisible(true);
	}

	public String getText ()
	{
		return this.nameTF.getText();
	}

	public void toggleTextField ()
	{
		this.nameTF.setEnabled( !this.nameTF.isEnabled() );
		if ( !this.nameTF.isEnabled() )
			this.nameTF.setText("");
	}

	public void actionPerformed (ActionEvent e)
	{
		if 		( e.getSource() == this.addB )
		{
			e.setSource(this);
			this.parent.actionPerformed( e );
		}
	}
}


