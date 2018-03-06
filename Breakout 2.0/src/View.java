
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
	private GOval ball = new GOval(0,0, Model.BALL_RAD, Model.BALL_RAD);
	private GRect paddel = new GRect(0,0, Model.PADDLE_WIDTH, Model.PADDLE_HEIGHT);
	private GRect[] brick = new GRect[100];
	private Model model;
	private Control control;

	/**
	 * @param model
	 * 
	 */


	public void update() {
		setSize(Model.WINDOW_WIDTH, Model.WINDOW_HEIGHT);
		addPaddle();
		addBall();
		addBricks();
		
		
	}

	public View(Model model, Control control) {
		// TODO Auto-generated constructor stub
		addKeyListeners(control);
		this.model = model;
		this.control = control;
		
	}
	private void addBricks() {
		for(int i = 0; i < brick.length; i++) {
			if (model.activeBricks[i] != false) {
				GRect brickI = new GRect(model.brickPosition[i].getX(), model.brickPosition[i].getY(), Model.BRICK_WIDTH, Model.BRICK_HEIGHT);
				brickI.setFilled(true);
				brickI.setFillColor(new Color(0, 255 , 0));
				add(brickI);
			} else if (brick[i] != null){
				remove(brick[i]);
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
