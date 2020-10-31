package game_life;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class panel extends JPanel
{
	public Timer tick;
	int speed = 300, time = 0, step = 0, cells = 0;
	boolean deadeend = false, twostep = false, creating = false, earsing = false;
	String state = "Running";
	
	public panel()
	{
		setPreferredSize(new Dimension(1000,1000));
		setFocusable(true);
		addMouseListener(new CustomListener());
		addMouseMotionListener(new CustomMotionListener());
		
		tick = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	time += 100;
            	
            	if(speed <= time)
            	{
            		time = 0;
            		tick();
            	}
            }
		});
		
		addKeyListener(new KeyListener()
		{
		    public void keyPressed(KeyEvent e)
		    {
		    	int code = e.getKeyCode();
		    	switch(code)
		    	{
		    		case(32):
			    		if(tick.isRunning())
			    		{
			    			tick.stop();
			    			repaint();
			    		}
			    		else
			    		{
			    			tick.start();
			    		}
		    		break;
		    		
		    		case(39):
		    			tick();
		    		break;
		    		
		    		case(38):
		    			if(speed>100)
		    				speed -= 100 ;
		    		break;
		    		
		    		case(40):
		    			if(speed<2000)
		    				speed += 100 ;
		    		break;
		    		
		    		case(27):
		    			System.exit(0);
		    		break;
		    	}
		    	repaint();
		    }
		    public void keyReleased(KeyEvent e) {}
		    public void keyTyped(KeyEvent e) {}
		});
	}
	
	public class CustomListener implements MouseListener
	{
        
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        
        public void mouseClicked(MouseEvent e)
        {
        	if(e.getButton() == 1)
        	{
        		main.newcells[e.getY()/10][e.getX()/10] = !main.newcells[e.getY()/10][e.getX()/10];
        		repaint();
        	}
        	
        }
        
        public void mousePressed(MouseEvent e)
        {
        	
        	if(e.getButton() == 1)
        	{
        		if(!main.newcells[e.getY()/10][e.getX()/10])
        			creating = true;
        		else
        			earsing = true;
        	}
        }

        public void mouseReleased(MouseEvent e)
        {
        	if(e.getButton() == 1)
        	{
        		creating = false;
        		earsing = false;
        	}
        }
   }
	
	public class CustomMotionListener implements MouseMotionListener
	{
		public void mouseDragged(MouseEvent e)
		{
			if(creating)
				main.newcells[e.getY()/10][e.getX()/10] = true;
			if(earsing)
				main.newcells[e.getY()/10][e.getX()/10] = false;
			repaint();
		}
		
		public void mouseMoved(MouseEvent e) {}
		
	}
	
	public void tick()
	{
		step++;
		deadeend = true;
		twostep = true;
		for(int i=0; i<100; i++)
		{
			for(int j=0; j<100; j++)
			{
				int neighbors = check(i, j);
				
				if(neighbors == 2 && main.cells[i][j])
				{
					main.newcells[i][j] = true;
				}
				else if(neighbors == 3)
				{
					main.newcells[i][j] = true;
				}
				else
				{
					main.newcells[i][j] = false;
				}
				main.newtwostepcells[i][j] = main.cells[i][j];
				
				if(main.twostepcells[i][j]^main.newcells[i][j])
					twostep = false;
				
				if((main.cells[i][j]^main.newcells[i][j]))
					deadeend = false;
			}
		}
    	repaint();
	}
	
	int check(int y, int x)
	{
		int neighbors=0;
		
		for(int i=-1; i<2; i++)
		{
			for(int j=-1; j<2; j++)
			{
				if(x+j>-1 && x+j<100 && y+i>-1 && y+i<100)
				{
					if(i==0 && j==0)
						continue;
					if(main.cells[y+i][x+j])
						neighbors++;
				}
			}
		}
		return neighbors;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		cells = 0;
		
		for(int i=0; i<100; i++)
		{
			for(int j=0; j<100; j++)
			{
				main.cells[i][j] = main.newcells[i][j];
				
				if(main.cells[i][j])
				{
					g.fillRect(j*10, i*10, 10, 10);
					cells++;
				}
				main.twostepcells[i][j] = main.newtwostepcells[i][j];
			}
		}
		
		if(tick.isRunning())
			state = "Running";
		else
			state = "Pause";
		
		if(twostep)
			state = "Two-step oscillator";
		
		if(deadeend)
			state = "Dead end";
		
		if(cells == 0)
			state = "Dead";
		
		g.setColor(Color.RED);
		
		g.drawString(state, 0, 10);
		g.drawString("Interval      "+speed+"ms", 0, 25);
		g.drawString("Iteration     "+step, 0, 40);
		g.drawString("Cells         "+cells, 0, 55);
	}
}
