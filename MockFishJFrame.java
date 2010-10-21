/**
 * JFrame to contain the JPanel
*/
import java.awt.*;
import javax.swing.*;

public class MockFishJFrame extends JFrame {

	 public MockFishJFrame( String title, int x, int y, int width, int height ) {
		setBounds( x, y, width, height );
		setTitle( title );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		Container visibleArea = getContentPane();
		visibleArea.setBounds(0, 0, 640, 480 );
		JPanel myPanel = new MockFishJPanel();
		visibleArea.add(myPanel);
		setVisible(true);
	 }
}
