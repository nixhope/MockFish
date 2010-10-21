A very simple and quite poor fish simulator.

Fish movement is based on a few general premises:
1: if there are fish close by, all fish will tend to go in the same direction
2: if a fish collides with another fish, it will try to move away
3: if too many fish are very close, a fish will eventually die (this may be adjusted by changing the c.LIFE variable; fish heal some % health per timer tick)

Adjust the following constants in c.java to alter the simulation:
	public static final int WIDTH = 640-15;
	public static final int HEIGHT = 480-35;
	public static final int SIZE = MED;
	public static final int LIFE = 50;
	public static final int SPEED = 5; // hundredth lengths moved per timer tick
	public static final int MAXFISH = 10;

For more exciting changes, good luck with the Fish.java code!