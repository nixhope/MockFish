/**
  * NixHope
  * v0.0.1a October 2010
  * Contains the main JPanel
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MockFishJPanel extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener {
	Timer t;
	int size, fishCount;
	boolean mouseMoved, mousePressed;
	Point mouseCurrentLocation, mousePressedLocation;
	Fish[] myFish;

	// Initialise snake
	public MockFishJPanel() {
		addKeyListener( this );
		addMouseListener( this );
		addMouseMotionListener ( this );
		t = new Timer(25, this);
		size = c.SIZE;
		mouseMoved = false;
		
		fishCount = 0;
		myFish = new Fish[c.MAXFISH];
		t.start();
	}

	// Draw stuff
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		g.setColor(new Color(5, 6, 132));
		g.fillRect(0, 0, c.WIDTH, c.HEIGHT);
		for (int i = 0; i < fishCount; i++)
			myFish[i].draw(g);
		
		// Testing:
		/*
		int n = 100;
		Rectangle r = new Rectangle(100,200,n,n);
		
		g.setColor(Color.GREEN);
		g.drawOval(r.x+15*n/16-5*n/8, r.y+n/4, 5*n/8, n/2);
		g.drawOval(r.x+25*n/32-n/16, r.y+11*n/32, n/16, n/16);
		g.drawLine(r.x+15*n/16-5*n/8, r.y+n/2, r.x+n/16, r.y+n/4);
		g.drawLine(r.x+15*n/16-5*n/8, r.y+n/2, r.x+n/16, r.y+3*n/4);
		g.drawLine(r.x+n/16, r.y+n/4, r.x+n/16, r.y+3*n/4);
		
		r = new Rectangle(300,200,n,n);
		g.drawRect(r.x, r.y, n, n);
		g.drawOval(r.x+n/4, r.y+15*n/16-5*n/8, n/2, 5*n/8);
		g.drawOval(r.x+11*n/32, r.y+25*n/32-n/16, n/16, n/16);
		g.drawLine(r.x+n/2, r.y+15*n/16-5*n/8, r.x+n/4, r.y+n/16);
		g.drawLine(r.x+n/2, r.y+15*n/16-5*n/8, r.x+3*n/4, r.y+n/16);
		g.drawLine(r.x+n/4, r.y+n/16, r.x+3*n/4, r.y+n/16);*/
	}
	
	private void addFish() {
		if (fishCount < myFish.length)
		{
			myFish[fishCount] = new Fish(size, myFish);
			fishCount++;
		} else {
			for (int i = 0; i < fishCount; i++) {
				if (!myFish[i].alive) {
					for (int j = i+1; j < myFish.length; j++) {
						myFish[j-1] = myFish[j];
					}
					myFish[myFish.length-1] = null;
					fishCount--;
				}
			}
		}
	}

	public void actionPerformed(java.awt.event.ActionEvent e)
	{
		for (int i = 0; i < fishCount; i++)
			myFish[i].move(myFish);
		repaint();
	}

	public void keyPressed( KeyEvent e ) {
	}

	public void keyReleased( KeyEvent newEvent ) { }
	public void keyTyped( KeyEvent newEvent ) { }

  	public void mousePressed( MouseEvent newEvent ) {
		mousePressedLocation = new Point(newEvent.getX(),newEvent.getY());
		mousePressed = !mousePressed;
		addFish();
		repaint();
  	}
	
	public void mouseMoved( MouseEvent newEvent ) { 
		mouseCurrentLocation = new Point(newEvent.getX(),newEvent.getY());
		mouseMoved = true;
		repaint();
	}
	
	public void mouseReleased( MouseEvent newEvent ) { }
	public void mouseClicked( MouseEvent newEvent ) { }
	public void mouseEntered( MouseEvent newEvent ) { repaint(); }
	public void mouseExited( MouseEvent newEvent ) { }
	
	public void mouseDragged( MouseEvent newEvent ) { }
}

/*

*/
