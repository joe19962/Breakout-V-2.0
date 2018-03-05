import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 */

/**
 * @author J.Masche S.Scheible
 *
 */
public class Control implements KeyListener {

	Model model;

	public Control(Model model) {
		// TODO Auto-generated constructor stub

		this.model = model;

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'A' || e.getKeyChar() == 'a') {
			double xPos = model.paddlePosition.getX();
			model.paddlePosition.setX(xPos - 4);
		}
		if (e.getKeyChar() == 'D' || e.getKeyChar() == 'd') {
			double xPos = model.paddlePosition.getX();
			model.paddlePosition.setX(xPos + 4);
		}
	}

}
