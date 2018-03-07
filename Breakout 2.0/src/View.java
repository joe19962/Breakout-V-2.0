import java.awt.Color;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.io.IODialog;
import acm.program.*;

/**
 * 
 */

/**
 * @author J.Masche S.Scheible
 *
 */
public class View extends GraphicsProgram {

	/**
	 * 
	 */
	public GOval ball = new GOval(0, 0, Constants.BALL_RAD, Constants.BALL_RAD);
	public GRect paddel = new GRect(0, 0, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);
	public GRect[] brick = new GRect[100];
	private Model model;

	/**
	 * @param model
	 * 
	 */

	public void update() {
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		addPaddle();
		addBall();
		removeBricks();

	}

	private void removeBricks() {
		for (int i = 0; i < brick.length; i++) {
			if (model.brickPosition[i] != null && model.activeBricks[i] == false) {
				model.brickPosition[i] = null;
				remove(brick[i]);
			}
		}
	}

	public View(Model model, Control control) {
		// TODO Auto-generated constructor stub
		addKeyListeners(control);
		this.model = model;
		addBricks();

	}

	private void addBricks() {
		for (int i = 0; i < brick.length; i++) {
			if (model.activeBricks[i] != false) {
				brick[i] = new GRect(model.brickPosition[i].getX(), model.brickPosition[i].getY(), Constants.BRICK_WIDTH,
						Constants.BRICK_HEIGHT);
				brick[i].setFilled(true);
				brick[i].setFillColor(new Color(0, 255, 0));
				add(brick[i]);

			}
		}
	}

	private void addPaddle() {

		paddel.setFilled(true);
		paddel.setFillColor(Color.BLACK);
		add(paddel);
		paddel.setLocation(model.paddlePosition.getX(), model.paddlePosition.getY());

	}

	private void addBall() {
		ball.setFilled(true);
		ball.setFillColor(Color.RED);
		add(ball);

		ball.setLocation(model.ballPosition.getX(), model.ballPosition.getY());

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
