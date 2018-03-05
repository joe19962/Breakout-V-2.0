import java.awt.Color;

/**
 * @author J.Masche S.Scheible
 *
 */
public class Model {
	private int directionx;
	private int directiony;
	private static int level = 0;//(int) (Math.random() * 2);
	private double vx = Math.random();
	private double vy = -Math.random();
	public static final int WINDOW_WIDTH = 520;
	public static final int WINDOW_HEIGHT = 1000;
	public static final int PADDLE_WIDTH = 100;
	public static final int PADDLE_HEIGHT = 10;
	public static final int BALL_RAD = 15;
	public static final int BRICK_WIDTH = 50;
	public static final int BRICK_HEIGHT = 20;
	
	public boolean collisonWall = false;
	public boolean collisonBrick = false;
	public static int brick_counter = 1;
	private static boolean running = false;
	
	/**
	 * 
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		Control control = new Control();
		View view = new View(model, control);
		view.start();
		view.addKeyListeners();
		view.init();
		ConstructBall(view);
		ConstructPaddel(view);
		ConstructBrick(view);
		view.update();
		running = true;
		while (running) {
			if (brick_counter == 0) {
				running= false;
				view.gamewin();
			}
			if (view.ball.getY()>= WINDOW_HEIGHT) {
				running = false;
				view.gameOver();
			}
		
		}

	}

	

	private static void ConstructBrick(View view) {
		// TODO Auto-generated method stub
		int y = 0;
		int x = 0;
		int x1 = 0;
		switch (level) {
		case (0):
			brick_counter = 0;
			// First Level
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 11; j++) {

					if (j % 3 == 0) {
					} else {
						y = 20 + j * BRICK_HEIGHT;
						x = i * BRICK_WIDTH;
						
						view.brick[brick_counter].setBounds(x, y, BRICK_WIDTH, BRICK_HEIGHT);
						view.brick[brick_counter].setFilled(true);
						view.brick[brick_counter].setFillColor(new Color(0, 255 / (j + 1), 0));
						
						brick_counter++;
					}
				}

			}
			break;

		case (1):
			// Map 2
			// Looks ok, needs Color though
			brick_counter = 0;
			int j = 0;
			for (int i = 0; i < 5; i++) {

				 x = BRICK_WIDTH * (i + 2);
				 y = BRICK_HEIGHT * j;
				x1 = BRICK_WIDTH * (6 - i);
				view.brick[brick_counter].setBounds(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick_counter++;
				view.brick[brick_counter].setBounds(x1, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick_counter++;
				++j;
				y = BRICK_HEIGHT * j;
				view.brick[brick_counter].setBounds(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick_counter++;
				view.brick[brick_counter].setBounds(x1, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick_counter++;
				j++;
			}
			int k = 10;
			for (int l = 0; l < 5; l++) {
				x = BRICK_WIDTH * (l + 2);
				y = BRICK_HEIGHT * k;
				x1 = BRICK_WIDTH * (6 - l);
				view.brick[brick_counter].setBounds(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick_counter++;
				view.brick[brick_counter].setBounds(x1, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick_counter++;
				++k;
				y = BRICK_HEIGHT * k;
				view.brick[brick_counter].setBounds(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick_counter++;
				view.brick[brick_counter].setBounds(x1, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick_counter++;
				k++;

			}
			break;

		case (2):
			// Map 3

		default:
			throw new IllegalArgumentException("Something went srsly wrong with the Randomgenerator");
		}
	}

	private static void ConstructPaddel(View view) {
		// TODO Auto-generated method stub
		view.paddel.setLocation((WINDOW_WIDTH / 2) - (PADDLE_WIDTH / 2),  WINDOW_HEIGHT - (WINDOW_HEIGHT / 5));
		
	}

	private static void ConstructBall(View view) {
		// TODO Auto-generated method stub
		view.ball.setLocation(WINDOW_WIDTH / 2,WINDOW_HEIGHT - (WINDOW_HEIGHT / 5) );
		
	}

	public void ballmovement(View view) {
		// TODO Auto-generated method stub
		Collison(view);
		if (vx < 0 ) {
		directionx= -5;
		}else {
			directionx = 5;
		}
		if (vy < 0 ) {
		directiony= -5;	
		}else {
			directiony = 5;
		}
		
		
		view.ball.setLocation(view.ball.getX()+directionx, view.ball.getY()+directiony);
	}

	private void Collison(View view) {
		WallColison(view);
		BrickColison(view);
		PaddelColison(view);

	}

	private void PaddelColison(View view) {
		// TODO Auto-generated method stub
		if (view.ball.getY() + BALL_RAD == PADDLE_HEIGHT && (view.ball.getX() >= view.paddel.getX()
				&& view.ball.getX() <= (view.paddel.getX() + PADDLE_WIDTH))) {
			vy = -vy;
		}
		if (view.ball.getX() + BALL_RAD == view.paddel.getX() && (view.ball.getY() >= view.paddel.getY()
				&& view.ball.getY() <= (view.paddel.getY() + PADDLE_HEIGHT))) {
			vx = -vx;
		}
		if (view.ball.getX() + BALL_RAD == (view.paddel.getX() + PADDLE_WIDTH)
				&& (view.ball.getY() >= view.paddel.getY()
						&& view.ball.getY() <= (view.paddel.getY() + PADDLE_HEIGHT))) {
			vx = -vx;
		}
	}

	private void BrickColison(View view) {
		// TODO Auto-generated method stub
		int n = brick_counter;
		for (int i = 0; i < n; i++) {
			// oberkante Brick
			if (view.ball.getY() + BALL_RAD == view.brick[i].getY() && (view.ball.getX() >= view.brick[i].getX()
					&& view.ball.getX() <= (view.brick[i].getX() + BRICK_WIDTH))) {
				
				brick_counter--;
				vy = -vy;
				view.brick[i] = null;
			}
			// unterkante Brick
			if (view.ball.getY() + BALL_RAD == view.brick[i].getY() + BRICK_HEIGHT
					&& (view.ball.getX() >= view.brick[i].getX()
							&& view.ball.getX() <= (view.brick[i].getX() + BRICK_WIDTH))) {
				
				brick_counter--;
				vy = -vy;
				view.brick[i] = null;
			}
			// linkeSeite Brick
			if (view.ball.getX() == view.brick[i].getX() && view.ball.getY() <= view.brick[i].getY()
					&& view.ball.getY() <= view.brick[i].getY() + BRICK_HEIGHT) {
				
				brick_counter--;
				vx = -vx;
				view.brick[i] = null;
			}
			// rechteSeite Brick
			if (view.ball.getX() == view.brick[i].getX() + BRICK_WIDTH && view.ball.getY() <= view.brick[i].getY()
					&& view.ball.getY() <= view.brick[i].getY() + BRICK_HEIGHT) {
				
				brick_counter--;
				vx = -vx;
				view.brick[i] = null;
			}
		}

	}


	private void WallColison(View view) {
		// TODO Auto-generated method stub
		if ((view.ball.getX() - vx <= 0 && vx < 0)
				|| (view.ball.getX() + vx >= (WINDOW_WIDTH) - BALL_RAD * 2) && vx > 0) {
			vx = vx * -1;

		} // Seitenwaende
		if (view.ball.getY() <= 0) {
			vy = vy * -1;

		} // Decke
	}
}
