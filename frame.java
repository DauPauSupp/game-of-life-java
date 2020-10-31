package game_life;

import javax.swing.*;

public class frame extends JFrame
{
	public frame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game Life");
		setLocation(400, 0);
		setContentPane(main.MainPanel);
		pack();
		setVisible(true);
	}
}
