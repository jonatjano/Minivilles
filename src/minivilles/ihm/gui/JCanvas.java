import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


class JCanvas extends JPanel
{
	public JCanvas (Dimension dim)
	{
		super();
		setPreferredSize(dim);
	}

	@Override
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		g.drawString("BLAH", 20, 20);
		g.drawRect(200, 200, 200, 200);
	}
}