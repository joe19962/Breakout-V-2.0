
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
	public GRect[] brick = new GRect[100];
	public Model model;
	public Control control;

	/**
	 * @param model
	 * 
	 */

	public void init() {
		setSize(Model.WINDOW_WIDTH, Model.WINDOW_HEIGHT);
		ball = new GOval(0,0,Model.BALL_RAD, Model.BALL_RAD);
		paddel = new GRect(0,0,Model.PADDLE_WIDTH,Model.PADDLE_HEIGHT);
		for (int i = 0; i < brick.length; i++) {
			brick[i] = new GRect(0, 0, Model.BRICK_WIDTH, Model.BRICK_HEIGHT);
			brick[i].setSize(0, 0);
		}
	}

	public void update() {
		remove(ball);
		remove(paddel);
		add(ball);
		add(paddel);
		for (int i = 0; i < brick.length; i++) {
			if (brick[i].getY() != 0 && brick[i] != null) {
			add(brick[i]);
			}
		}
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
