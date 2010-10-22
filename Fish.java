/**
*/

import java.awt.*;
public class Fish {
	Rectangle r; // Approximation of the fish
	int n; // Size
	int direction;
	public boolean alive;
	int life, move, unhappyCount;
	int type;
	Color color;
	
	public Fish(int size, Fish[] fishArray, Point p) {
		n = size;
		r = new Rectangle(p.x, p.y, n, n);
		life = c.LIFE;
		move = n*c.SPEED/100;
		direction = (int)(Math.random()*4);
		alive = true;
		type = (int)(Math.random()*1);
		color = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
	}
	
	public boolean intersects(Rectangle p) { return r.intersects(p); }
	
	public void move(Fish[] fishArray) {
		// Move the fish:
		int d = direction;
			
		if (Math.random() > 0.9995) // Take a rest
			direction = c.STATIC;
		// Check for proximity to give directions and take damage
		int k = 0;
		int highCount = 0;
		int[] directionCount = new int[5];
		if (life < c.LIFE*1.5)
				life += c.LIFE/10; // Natural healing
		int unhappy = 0;
		while (fishArray[k] != null && k < fishArray.length-1) {
			if (this.intersects(new Rectangle(fishArray[k].getX()-2*n,fishArray[k].getY()-2*n,n*4,n*4)))
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (this.intersects(new Rectangle(fishArray[k].getX()+i*n,fishArray[k].getY()+j*n,n,n)) && fishArray[k].alive)
						life--;
				}
			}	
			if (this.intersects(new Rectangle(fishArray[k].getX()-2*n,fishArray[k].getY()-2*n,n*4,n*4)))
			{
				directionCount[fishArray[k].getDirection()]++;
				if (directionCount[fishArray[k].getDirection()] > highCount)
					highCount = directionCount[fishArray[k].getDirection()];
			}
			if (this.intersects(new Rectangle(fishArray[k].getX(),fishArray[k].getY(),n,n)))
				unhappy++;
			k++;
		}
		directionCount[c.STATIC] = 0;
		for (int i = 0; i<5; i++)
		{
			if (directionCount[i] == highCount)
				direction = i;
		}
		if (unhappyCount > 0) {
			unhappyCount--;
			direction = d;
		}
		if (unhappy > 1 && unhappyCount < 10) {
			unhappyCount = 20;
			if (Math.random() > 0.5) {
				direction = (direction+1)%5;
				if (direction == c.STATIC)
					direction = (direction+1)%5;
			} else {
				direction = (direction+4)%5;
				if (direction == c.STATIC)
					direction = (direction+4)%5;
			}
		}
		if (life <=0)
			alive = false;
		
		if (Math.random() > 0.98) // So they don't always do the same thing
			direction = (int)(Math.random()*4);
		
		if (direction == c.STATIC && Math.random() > 0.99) // Make sure they don't get stuck in a spot for too long
			direction = (int)(Math.random()*4);
		
		// Check for collisions with walls
		if (direction == c.UP && r.y-move <=0) {
			
			if (Math.random() > 0.4)
				direction = c.DOWN;
			else
				direction = c.STATIC;
		} else if (direction == c.RIGHT && r.x+move+n >= c.WIDTH) {
			if (Math.random() > 0.4)
				direction = c.LEFT;
			else
				direction = c.STATIC;
		} else if (direction == c.DOWN && r.y+move+n >= c.HEIGHT) {
			if (Math.random() > 0.4)
				direction = c.UP;
			else
				direction = c.STATIC;
		} else if (direction == c.LEFT && r.x-move <= 0) {
			if (Math.random() > 0.4)
				direction = c.RIGHT;
			else
				direction = c.STATIC;
		}
		
		if (r.x < 0)
			r.x = 0;
		else if (r.x+n > c.WIDTH)
			r.x = c.WIDTH-n;
		else if (r.y < 0)
			r.y = 0;
		else if (r.y +n > c.HEIGHT)
			r.y = c.HEIGHT-n;
		
		if (alive)
		{
			int m = (int)((Math.random()*5)*move);
			if (direction == c.UP)
				r.y = r.y-m;
			else if (direction == c.RIGHT)
				r.x = r.x+m;
			else if (direction == c.DOWN)
				r.y = r.y+m;
			else if (direction == c.LEFT)
				r.x = r.x-m;
		} else if (r.y > n/4) { // Dead fish only move upwards
			r.y = r.y-n/4;
		} else {r.y = 0; }
	}
	
	public int getDirection() { return direction; }
	
	public int getX() { return r.x; }
	
	public int getY() { return r.y; }

	// Draw
	public void draw(Graphics g) {
		if (alive)
			g.setColor(color);
		else
			g.setColor(Color.black);
		
		if (type == 0) {
			if (alive) {
				if (direction == c.UP) {
					g.drawOval(r.x+n/4, r.y+n/16, n/2, 5*n/8);
					g.drawOval(r.x+11*n/32, r.y+7*n/32, n/16, n/16);
					g.drawLine(r.x+n/2, r.y+n/16+5*n/8, r.x+n/4, r.y+n-n/16);
					g.drawLine(r.x+n/2, r.y+n/16+5*n/8, r.x+3*n/4, r.y+n-n/16);
					g.drawLine(r.x+n/4, r.y+n-n/16, r.x+3*n/4, r.y+n-n/16);
				} else if (direction == c.RIGHT) {
					g.drawOval(r.x+15*n/16-5*n/8, r.y+n/4, 5*n/8, n/2);
					g.drawOval(r.x+25*n/32-n/16, r.y+11*n/32, n/16, n/16);
					g.drawLine(r.x+15*n/16-5*n/8, r.y+n/2, r.x+n/16, r.y+n/4);
					g.drawLine(r.x+15*n/16-5*n/8, r.y+n/2, r.x+n/16, r.y+3*n/4);
					g.drawLine(r.x+n/16, r.y+n/4, r.x+n/16, r.y+3*n/4);
				} else if (direction == c.DOWN) {
					g.drawOval(r.x+n/4, r.y+15*n/16-5*n/8, n/2, 5*n/8);
					g.drawOval(r.x+11*n/32, r.y+25*n/32-n/16, n/16, n/16);
					g.drawLine(r.x+n/2, r.y+15*n/16-5*n/8, r.x+n/4, r.y+n/16);
					g.drawLine(r.x+n/2, r.y+15*n/16-5*n/8, r.x+3*n/4, r.y+n/16);
					g.drawLine(r.x+n/4, r.y+n/16, r.x+3*n/4, r.y+n/16);
				} else if (direction == c.LEFT) {
					g.drawOval(r.x+n/16, r.y+n/4, 5*n/8, n/2);
					g.drawOval(r.x+7*n/32, r.y+11*n/32, n/16, n/16);
					g.drawLine(r.x+n/16+5*n/8, r.y+n/2, r.x+n-n/16, r.y+n/4);
					g.drawLine(r.x+n/16+5*n/8, r.y+n/2, r.x+n-n/16, r.y+3*n/4);
					g.drawLine(r.x+n-n/16, r.y+n/4, r.x+n-n/16, r.y+3*n/4);
				} else {
					g.drawOval(r.x+n/16, r.y+n/4, 5*n/8, n/2);
					g.drawOval(r.x+7*n/32, r.y+11*n/32, n/16, n/16);
					g.drawLine(r.x+n/16+5*n/8, r.y+n/2, r.x+n-n/16, r.y+n/4);
					g.drawLine(r.x+n/16+5*n/8, r.y+n/2, r.x+n-n/16, r.y+3*n/4);
					g.drawLine(r.x+n-n/16, r.y+n/4, r.x+n-n/16, r.y+3*n/4);
				}
			} else {
				if (Math.random() > 1) {
					g.drawOval(r.x+n/16, r.y+n/4, 5*n/8, n/2);
					g.drawOval(r.x+7*n/32, r.y+11*n/32, n/16, n/16);
					g.drawLine(r.x+n/16+5*n/8, r.y+n/2, r.x+n-n/16, r.y+n/4);
					g.drawLine(r.x+n/16+5*n/8, r.y+n/2, r.x+n-n/16, r.y+3*n/4);
					g.drawLine(r.x+n-n/16, r.y+n/4, r.x+n-n/16, r.y+3*n/4);
				} else {
					g.drawOval(r.x+15*n/16-5*n/8, r.y+n/4, 5*n/8, n/2);
					g.drawOval(r.x+25*n/32-n/16, r.y+11*n/32, n/16, n/16);
					g.drawLine(r.x+15*n/16-5*n/8, r.y+n/2, r.x+n/16, r.y+n/4);
					g.drawLine(r.x+15*n/16-5*n/8, r.y+n/2, r.x+n/16, r.y+3*n/4);
					g.drawLine(r.x+n/16, r.y+n/4, r.x+n/16, r.y+3*n/4);
				}
			}
		}
		
		if (type == 1) {
			if (alive) {
				if (direction == c.UP) {
					g.drawRect(r.x, r.y, n, n);
				} else if (direction == c.RIGHT) {
					g.drawRect(r.x, r.y, n, n);
				} else if (direction == c.DOWN) {
					g.drawRect(r.x, r.y, n, n);
				} else if (direction == c.LEFT) {
					g.drawRect(r.x, r.y, n, n);
				} else {
					g.drawRect(r.x, r.y, n, n);
				}
			} else {
				if (Math.random() > 0.5) {
					g.fillRect(r.x, r.y, n, n);
				} else {
					g.fillRect(r.x, r.y, n, n);
				}
			}
		}
		
		if (type == 2) {
			if (alive) {
				if (direction == c.UP) {
					// draw facing up
				} else if (direction == c.RIGHT) {
					// draw facing right
				} else if (direction == c.DOWN) {
					// draw facing down
				} else if (direction == c.LEFT) {
					// draw facing left
				} else {
					// draw static
				}
			} else {
				if (Math.random() > 0.5) {
					//draw dead to left
				} else {
					//draw dead to right
				}
			}
		}
		
		if (type == 3) {
			if (alive) {
				if (direction == c.UP) {
					// draw facing up
				} else if (direction == c.RIGHT) {
					// draw facing right
				} else if (direction == c.DOWN) {
					// draw facing down
				} else if (direction == c.LEFT) {
					// draw facing left
				} else {
					// draw static
				}
			} else {
				if (Math.random() > 0.5) {
					//draw dead to left
				} else {
					//draw dead to right
				}
			}
		}
	}
	
	public void killFish() { alive = false; }
	
}