package game_life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main
{
	public static frame MainFrame;
	public static panel MainPanel;
	
	public static boolean[][] cells, newcells, twostepcells, newtwostepcells;
	
	public static void main(String[] args)
	{
		cells = new boolean[100][100];
		newcells = new boolean[100][100];
		twostepcells = new boolean[100][100];
		newtwostepcells = new boolean[100][100];
		
		//Glider
		newcells[0][1] = true;
		newcells[1][2] = true;
		newcells[2][0] = true;
		newcells[2][1] = true;
		newcells[2][2] = true;
		
		//Stick
		newcells[49][50] = true;
		newcells[49][49] = true;
		newcells[49][48] = true;
		
		//Block
		newcells[0][98] = true;
		newcells[0][99] = true;
		newcells[1][98] = true;
		newcells[1][99] = true;
		
		
		//Stick1
		newcells[69][50] = true;
		newcells[69][49] = true;
		newcells[69][48] = true;
		
		//Block1
		newcells[72][48] = true;
		newcells[72][49] = true;
		newcells[73][48] = true;
		newcells[73][49] = true;
		
		MainPanel = new panel();
		MainFrame = new frame();
	}
}