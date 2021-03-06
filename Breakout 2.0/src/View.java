import java.awt.Color;
import java.awt.Font;
import acm.graphics.*;
import acm.program.*;

/**
 * @author J.Masche S.Scheible
 *
 */
public class View extends GraphicsProgram {
	// GObjects for view
	public GOval ball = new GOval(0, 0, Model.BALL_RAD, Model.BALL_RAD);
	public GRect paddel = new GRect(0, 0, Model.PADDLE_WIDTH, Model.PADDLE_HEIGHT);
	public GRect[] brick = new GRect[100];
	private Model model;

	/**
	 * @param model
	 * 
	 */

	public void update() {
		setSize(Model.WINDOW_WIDTH, Model.WINDOW_HEIGHT);
		addPaddle();
		addBall();
		removeBricks();

	}

	private void addBall() {
		ball.setFilled(true);
		ball.setFillColor(Color.RED);
		add(ball);

		ball.setLocation(model.ballPosition.getX(), model.ballPosition.getY());

	}

	private void addPaddle() {

		paddel.setFilled(true);
		paddel.setFillColor(Color.BLACK);
		add(paddel);
		paddel.setLocation(model.paddlePosition.getX(), model.paddlePosition.getY());

	}

	private void addBricks() {
		for (int i = 0; i < brick.length; i++) {
			if (model.activeBricks[i] != false) {
				brick[i] = new GRect(model.brickPosition[i].getX(), model.brickPosition[i].getY(), Model.BRICK_WIDTH,
						Model.BRICK_HEIGHT);
				brick[i].setFilled(true);
				brick[i].setFillColor(new Color(0, 255, 0));
				add(brick[i]);

			}
		}
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

	public void gamewin() {
		// TODO Auto-generated method stub
		removeAll();
		GLabel winner = new GLabel("You are a Winner!!!", Model.WINDOW_WIDTH / 2, Model.WINDOW_HEIGHT / 2);
		winner.setFont(Font.SANS_SERIF);
		add(winner);

	}

	public void gameOver() {
		removeAll();
		GLabel loser = new GLabel(" You Lose!", Model.WINDOW_WIDTH / 2, Model.WINDOW_HEIGHT / 2);
		loser.setFont(Font.SANS_SERIF);
		add(loser);
	}

}
