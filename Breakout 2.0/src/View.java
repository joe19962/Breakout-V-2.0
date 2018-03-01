
import javax.swing.plaf.basic.BasicLabelUI;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.io.IODialog;
import acm.program.GraphicsProgram;

/**
 * 
 */

/**
 * @author J.Masche S.Scheible
 *
 */
public class View extends GraphicsProgram {
	public GOval ball;
	public GRect paddel;
	public GRect[] brick = new GRect[40];
	private Model model;
	public Control control;

	/**
	 * @param model
	 * 
	 */

	public void Update() {
	}

	public View(Model model, Control control) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.control = control;
	}

	public void gamewin() {
		// TODO Auto-generated method stub
		IODialog winner = new IODialog();
		ball.setVisible(false);
		winner.print("You Win!");
	}

	public void gameOver() {
		IODialog gameOver = new IODialog();
		ball.setVisible(false);
		gameOver.print("You Lost!");
	}

}
