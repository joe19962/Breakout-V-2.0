
/**
 * @author J.Masche S.Scheible
 *
 */
public class Model {

	private static int level = (0);
	// Constants
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 500;
	public static final int PADDLE_WIDTH = WINDOW_WIDTH / 5;
	public static final int PADDLE_HEIGHT = WINDOW_HEIGHT / 50;
	public static final int BALL_RAD = WINDOW_HEIGHT / 28;
	public static final int BRICK_WIDTH = WINDOW_WIDTH / 14;
	public static final int BRICK_HEIGHT = WINDOW_HEIGHT / 14;
	// Game Objects
	private Vector2D vec = new Vector2D(BALL_RAD, -BALL_RAD);
	public Vector2D ballPosition = new Vector2D(1, 1);
	public Vector2D paddlePosition = new Vector2D(1, 1);
	public Vector2D[] brickPosition = new Vector2D[100];
	public boolean[] activeBricks = new boolean[100];
	// Time and Ball variables
	private double first_time;
	private double new_time;
	private double deltaT;
	private int n = 0;
	private double ballSpeed = 10.0;

	// Collision booleans & Brick counter
	private boolean collisonVertical = false;
	private boolean collisonHorizontal = false;
	private boolean collisionhappened = false;
	public int brick_counter = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();

		model.ConstructBall();
		model.ConstructPaddel();
		model.ConstructBrick();
		double zufallsRotation = Math.toRadians((20 - (Math.random() * 40)));
		model.vec.rotate(zufallsRotation);

		Control control = new Control(model);

		View view = new View(model, control);
		Lighthouseupdate lighthouse = new Lighthouseupdate(model, control);

		view.start();
		view.waitForClick();
		model.first_time = System.nanoTime();
		while (true) {
			if (model.brick_counter == 0) {
				view.gamewin();
				break;
			}
			if (model.ballPosition.getY() >= WINDOW_HEIGHT) {
				view.gameOver();
				break;
			}

			model.new_time = model.first_time;
			model.first_time = System.nanoTime();
			model.deltaT = (model.first_time - model.new_time) * 0.000000001; // in seconds
			lighthouse.update();
			view.update();
			model.ballmovement();

			// System.out.println(model.ballPosition.getX() + "X " +
			// model.ballPosition.getY() + " Y");

			// Paddle movement and Ballmovement needs to be added.

		}

	}

	public void ballmovement() {
		// TODO Auto-generated method stub
		boolean collisionhappened = Collison();

		if (collisionhappened) {
			double x = vec.getX();
			double y = vec.getY();
			if (collisonVertical) {

				double alpha = Math.atan((x / y));

				// get the Angle of the collision
				vec = vec.rotate(alpha * 2);
				// rotate the vec 180degrees- twice the angle of collision
				collisonVertical = false;

				// return collisonVertical back to false for next collision

			}
			if (collisonHorizontal) {
				double alpha = Math.atan((-y / x));
				// big diff here, vecY / vecX instead of other way because horizontal collision
				// not vertical
				vec = vec.rotate(alpha * 2);
				// rotate the vec again
				collisonHorizontal = false;

			}
		}
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			System.out.println("sleep didnt work");
		}

		// System.out.println(vec.getX() + "X " + vec.getY() + "Y");
		Vector2D bewegung = vec.multiply(ballSpeed * deltaT);
		// vector_movement = vec_direction * scalar_speed per unit * deltaT(time passed
		// in one cycle);

		ballPosition = ballPosition.add(bewegung);

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Creation of Game Objects
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void ConstructBrick() {
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

						brickPosition[brick_counter] = new Vector2D(x + WINDOW_WIDTH / 6, y);
						activeBricks[brick_counter] = true;

						// view.brick[brick_counter].setBounds(x, y, BRICK_WIDTH, BRICK_HEIGHT);
						// view.brick[brick_counter].setFilled(true);
						// view.brick[brick_counter].setFillColor(new Color(0, 255 / (j + 1), 0));

						brick_counter++;
					}
				}

			}
			n = brick_counter;
			break;

		case (1):
			// Map 2
			// Looks ok, needs Color though
			brick_counter = 0;
			int j = 0;
			for (int i = 0; i < 4; i++) {
				x = BRICK_WIDTH * (i + 2) + WINDOW_WIDTH / 6;
				y = BRICK_HEIGHT * j;
				x1 = BRICK_WIDTH * (6 - i) + WINDOW_WIDTH / 6;
				brickPosition[brick_counter] = new Vector2D(x, y);
				activeBricks[brick_counter] = true;
				brick_counter++;
				brickPosition[brick_counter] = new Vector2D(x1, y);
				activeBricks[brick_counter] = true;
				brick_counter++;
				++j;
				// y = BRICK_HEIGHT * j;
				// brickPosition[brick_counter] = new Vector2D(x, y);
				// activeBricks[brick_counter] = true;
				// brick_counter++;
				// brickPosition[brick_counter] = new Vector2D(x1, y);
				// activeBricks[brick_counter] = true;
				// brick_counter++;
				// j++;
			}
			int k = 4;
			for (int l = 0; l < 4; l++) {
				x = BRICK_WIDTH * (l + 2) + WINDOW_WIDTH / 6;
				y = BRICK_HEIGHT * k;
				x1 = BRICK_WIDTH * (6 - l) + WINDOW_WIDTH / 6;
				brickPosition[brick_counter] = new Vector2D(x, y);
				activeBricks[brick_counter] = true;
				brick_counter++;
				brickPosition[brick_counter] = new Vector2D(x1, y);
				activeBricks[brick_counter] = true;
				brick_counter++;
				++k;
				// y = BRICK_HEIGHT * k;
				// brickPosition[brick_counter] = new Vector2D(x, y);
				// activeBricks[brick_counter] = true;
				// brick_counter++;
				// brickPosition[brick_counter] = new Vector2D(x1, y);
				// activeBricks[brick_counter] = true;
				// brick_counter++;
				// k++;
			}
			n = brick_counter;
			break;

		case (2):
			// Map 3
			int layerIndex = 10;
			int i = 0;
			while (layerIndex >= 0) {
				for (i = layerIndex; i >= 0; i--) {
					System.out.println(layerIndex + " " + i);
					x = (20 + 20 * i - 10 * layerIndex + 10 * layerIndex);
					y = (10 + 10 * layerIndex);
					brickPosition[brick_counter] = new Vector2D(x, y);
					activeBricks[brick_counter] = true;
					brick_counter++;
				}
				layerIndex--;
			}
			n = brick_counter;
			break;
		default:
			throw new IllegalArgumentException("Something went srsly wrong with the Randomgenerator");
		}
	}

	private void ConstructPaddel() {
		// TODO Auto-generated method stub
		paddlePosition.setX((WINDOW_WIDTH / 2) - (PADDLE_WIDTH / 2));
		paddlePosition.setY(WINDOW_HEIGHT - (PADDLE_HEIGHT * 6));

	}

	private void ConstructBall() {
		// TODO Auto-generated method stub
		ballPosition.setX(WINDOW_WIDTH / 2);
		ballPosition.setY(WINDOW_HEIGHT - (PADDLE_HEIGHT * 6) - (BALL_RAD * 2) - 1);

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Collision/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private boolean Collison() {

		boolean wall = WallColison();
		boolean brick = BrickColison();
		boolean paddel = PaddelColison();
		boolean ret = !collisionhappened;
		collisionhappened = wall || brick || paddel;
		return ret && collisionhappened;

	}

	private boolean PaddelColison() {
		// TODO Auto-generated method stub
		boolean collision = false;

		if ((ballPosition.getY() + BALL_RAD >= paddlePosition.getY()
				&& ballPosition.getY() + BALL_RAD <= paddlePosition.getY() + (PADDLE_HEIGHT / 2))

				&& ((ballPosition.getX() >= paddlePosition.getX())
						&& (ballPosition.getX() + BALL_RAD <= paddlePosition.getX() + PADDLE_WIDTH))) {

			collisonHorizontal = true;
			collision = true;

		} // Oberkante paddel
		if ((ballPosition.getX() + BALL_RAD >= paddlePosition.getX()
				&& ballPosition.getX() + BALL_RAD <= paddlePosition.getX() + (PADDLE_WIDTH / 2))
				&& ((ballPosition.getY() >= paddlePosition.getY()
						&& (ballPosition.getY() + BALL_RAD <= paddlePosition.getY() + PADDLE_HEIGHT)))) {

			collisonVertical = true;
			collision = true;
		} // linke Seite paddel
		if ((ballPosition.getX() <= paddlePosition.getX() + PADDLE_WIDTH
				&& ballPosition.getX() >= paddlePosition.getX() + (PADDLE_WIDTH / 2))
				&& ((ballPosition.getY() >= paddlePosition.getY()
						&& (ballPosition.getY() + BALL_RAD <= paddlePosition.getY() + PADDLE_HEIGHT)))) {

			collisonVertical = true;
			collision = true;
		} // rechte seite Paddel
		return collision;
	}

	private boolean BrickColison() {
		// TODO Auto-generated method stub
		boolean collision = false;
		for (int i = 0; i < n; i++) {
			if (brickPosition[i] != null) {
				// oberkante Brick
				if ((ballPosition.getY() + BALL_RAD >= brickPosition[i].getY()
						&& ballPosition.getY() + BALL_RAD <= brickPosition[i].getY() + (BRICK_HEIGHT / 2))

						&& ((ballPosition.getX() >= brickPosition[i].getX())
								&& (ballPosition.getX() + BALL_RAD <= brickPosition[i].getX() + BRICK_WIDTH))) {

					brick_counter--;

					activeBricks[i] = false;
					collisonHorizontal = true;
					collision = true;

				}
				// unterkante Brick
				if ((ballPosition.getY() <= brickPosition[i].getY() + BRICK_HEIGHT
						&& ballPosition.getY() >= brickPosition[i].getY() + (BRICK_HEIGHT / 2))
						&& (ballPosition.getX() >= brickPosition[i].getX()
								&& ballPosition.getX() <= brickPosition[i].getX() + BRICK_WIDTH)) {

					brick_counter--;

					activeBricks[i] = false;
					collisonHorizontal = true;
					collision = true;

				}
				// linkeSeite Brick
				if ((ballPosition.getX() + BALL_RAD >= brickPosition[i].getX()
						&& ballPosition.getX() + BALL_RAD <= brickPosition[i].getX() + (BRICK_WIDTH / 2))
						&& ((ballPosition.getY() >= brickPosition[i].getY()
								&& (ballPosition.getY() + BALL_RAD <= brickPosition[i].getY() + BRICK_HEIGHT)))) {

					brick_counter--;

					activeBricks[i] = false;
					collisonVertical = true;
					collision = true;

				}
				// rechteSeite Brick
				if ((ballPosition.getX() <= brickPosition[i].getX() + BRICK_WIDTH
						&& ballPosition.getX() >= brickPosition[i].getX() + (BRICK_WIDTH / 2))
						&& ((ballPosition.getY() >= brickPosition[i].getY()
								&& (ballPosition.getY() + BALL_RAD <= brickPosition[i].getY() + BRICK_HEIGHT)))) {

					brick_counter--;

					activeBricks[i] = false;
					collisonVertical = true;
					collision = true;

				}
			}

		}
		return collision;

	}

	private boolean WallColison() {
		// TODO Auto-generated method stub
		boolean collision = false;
		if ((ballPosition.getX() <= 0) || (ballPosition.getX() >= (WINDOW_WIDTH) - BALL_RAD)) {
			collisonVertical = true;
			collision = true;

		} // Seitenwaende
		if (ballPosition.getY() <= 0) {
			collisonHorizontal = true;
			collision = true;

		} // Decke
		return collision;
	}
}
