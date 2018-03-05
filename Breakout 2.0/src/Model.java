
/**
 * @author J.Masche S.Scheible
 *
 */
public class Model {
	public Vector2D ballPosition = new Vector2D(1, 1);
	public Vector2D paddlePosition = new Vector2D(1, 1);
	public Vector2D[] brickPosition = new Vector2D[100];
	private static int level = 0;// (int) (Math.random() * 2);
	public static final int WINDOW_WIDTH = 520;
	public static final int WINDOW_HEIGHT = 1000;
	public static final int PADDLE_WIDTH = 100;
	public static final int PADDLE_HEIGHT = 10;
	public static final int BALL_RAD = 15;
	public static final int BRICK_WIDTH = 50;
	public static final int BRICK_HEIGHT = 20;
	private static double first_time;
	private static double new_time;
	private static double deltaT;
	private double ballSpeed = 2.0;
	Vector2D vec = new Vector2D(0.75, -0.5);

	private boolean collisonVertical = false;
	private boolean collisonHorizontal = false;
	public int brick_counter = 1;
	private static boolean running = false;

	/**
	 * 
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		first_time = System.nanoTime();
		Model model = new Model();
		Control control = new Control(model);
		View view = new View(model, control);

		view.start();
		view.addKeyListeners();

		model.ConstructBall();
		model.ConstructPaddel();
		model.ConstructBrick();

		running = true;
		while (running) {
			if (model.brick_counter == 0) {
				running = false;
				view.gamewin();
			}
			if (model.ballPosition.getY() >= WINDOW_HEIGHT) {
				running = false;
				view.gameOver();
			}

			new_time = first_time;
			first_time = System.nanoTime();
			deltaT = (first_time - new_time) * 0.000000001; // in seconds
			model.ballmovement();
			view.update();
			// System.out.println(model.ballPosition.getX() + "X " +
			// model.ballPosition.getY() + " Y");

			// Paddle movement and Ballmovement needs to be added.

		}

	}

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

						brickPosition[brick_counter] = new Vector2D(x, y);

						// view.brick[brick_counter].setBounds(x, y, BRICK_WIDTH, BRICK_HEIGHT);
						// view.brick[brick_counter].setFilled(true);
						// view.brick[brick_counter].setFillColor(new Color(0, 255 / (j + 1), 0));

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
				brickPosition[brick_counter] = new Vector2D(x, y);
				brick_counter++;
				brickPosition[brick_counter] = new Vector2D(x1, y);
				brick_counter++;
				++j;
				y = BRICK_HEIGHT * j;
				brickPosition[brick_counter] = new Vector2D(x, y);
				brick_counter++;
				brickPosition[brick_counter] = new Vector2D(x1, y);
				brick_counter++;
				j++;
			}
			int k = 10;
			for (int l = 0; l < 5; l++) {
				x = BRICK_WIDTH * (l + 2);
				y = BRICK_HEIGHT * k;
				x1 = BRICK_WIDTH * (6 - l);
				brickPosition[brick_counter] = new Vector2D(x, y);
				brick_counter++;
				brickPosition[brick_counter] = new Vector2D(x1, y);
				brick_counter++;
				++k;
				y = BRICK_HEIGHT * k;
				brickPosition[brick_counter] = new Vector2D(x, y);
				brick_counter++;
				brickPosition[brick_counter] = new Vector2D(x1, y);
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

	private void ConstructPaddel() {
		// TODO Auto-generated method stub
		paddlePosition.setX((WINDOW_WIDTH / 2) - (PADDLE_WIDTH / 2));
		paddlePosition.setY(WINDOW_HEIGHT - (WINDOW_HEIGHT / 5));

	}

	private void ConstructBall() {
		// TODO Auto-generated method stub
		ballPosition.setX(WINDOW_WIDTH / 2);
		ballPosition.setY(WINDOW_HEIGHT - (WINDOW_HEIGHT / 5) - BALL_RAD - 1);

	}

	public void ballmovement() {
		// TODO Auto-generated method stub
		Collison();

		Vector2D normalVec = vec.normalize();
		// normalize the vec first
		if (collisonVertical) {

			double normalVecX = normalVec.getX();
			double normalVecY = normalVec.getY();
			// getX and getY
			double alpha = Math.toDegrees(Math.asin((normalVecX / normalVecY)));

			// get the Angle of the collision
			Vector2D rotated = normalVec.rotate(180 - (2 * alpha));
			// rotate the vec 180degrees- twice the angle of collision
			vec = rotated.multiply(vec.length());
			// return the vec back to it's original length
			collisonVertical = false;
			// return collisonVertical back to false for next collision

		}
		if (collisonHorizontal) {
			double normalVecX = normalVec.getX();
			double normalVecY = normalVec.getY();
			// get X and Y
			// System.out.println(normalVecX + "X");
			double alpha = Math.toDegrees(Math.asin((normalVecY / normalVecX)));
			// big diff here, vecY / vecX instead of other way because horizontal collision
			// not vertical
			Vector2D rotated = normalVec.rotate(180 - (2 * alpha));
			// rotate the vec again
			vec = rotated.multiply(vec.length());
			collisonHorizontal = false;
		}
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			System.out.println("sleep didnt work");
		}

		// System.out.println(vec.getX() + "X " + vec.getY() + "Y");
		Vector2D bewegung = vec.multiply(ballSpeed * deltaT);
		// vector_movement = vec_direction * scalar_speed per unit * deltaT(time passed
		// in one cycle);
		ballPosition = ballPosition.add(bewegung);

	}

	private void Collison() {
		WallColison();
		BrickColison();
		PaddelColison();

	}

	private void PaddelColison() {
		// TODO Auto-generated method stub
		if (ballPosition.getY() + BALL_RAD == PADDLE_HEIGHT && (ballPosition.getX() >= paddlePosition.getX()
				&& ballPosition.getX() <= (paddlePosition.getX() + PADDLE_WIDTH))) {

			collisonHorizontal = true;
		} // Oberkante paddel
		if (ballPosition.getX() + BALL_RAD == paddlePosition.getX() && (ballPosition.getY() >= paddlePosition.getY()
				&& ballPosition.getY() <= (paddlePosition.getY() + PADDLE_HEIGHT))) {

			collisonVertical = true;
		} // linke Seite paddel
		if (ballPosition.getX() + BALL_RAD == (paddlePosition.getX() + PADDLE_WIDTH)
				&& (ballPosition.getY() >= paddlePosition.getY()
						&& ballPosition.getY() <= (paddlePosition.getY() + PADDLE_HEIGHT))) {

			collisonVertical = true;
		} // rechte seite Paddel
	}

	private void BrickColison() {
		// TODO Auto-generated method stub
		int n = brick_counter;
		for (int i = 0; i < n; i++) {
			// oberkante Brick
			if (ballPosition.getY() + BALL_RAD == brickPosition[i].getY()
					&& (ballPosition.getX() >= brickPosition[i].getX()
							&& ballPosition.getX() <= (brickPosition[i].getX() + BRICK_WIDTH))) {

				brick_counter--;

				brickPosition[i] = null;
				collisonHorizontal = true;
			}
			// unterkante Brick
			if (ballPosition.getY() + BALL_RAD == brickPosition[i].getY() + BRICK_HEIGHT
					&& (ballPosition.getX() >= brickPosition[i].getX()
							&& ballPosition.getX() <= (brickPosition[i].getX() + BRICK_WIDTH))) {

				brick_counter--;

				brickPosition[i] = null;
				collisonHorizontal = true;
			}
			// linkeSeite Brick
			if (ballPosition.getX() == brickPosition[i].getX() && ballPosition.getY() <= brickPosition[i].getY()
					&& ballPosition.getY() <= brickPosition[i].getY() + BRICK_HEIGHT) {

				brick_counter--;

				brickPosition[i] = null;
				collisonVertical = true;
			}
			// rechteSeite Brick
			if (ballPosition.getX() == brickPosition[i].getX() + BRICK_WIDTH
					&& ballPosition.getY() <= brickPosition[i].getY()
					&& ballPosition.getY() <= brickPosition[i].getY() + BRICK_HEIGHT) {

				brick_counter--;

				brickPosition[i] = null;
				collisonVertical = true;
			}
		}

	}

	private void WallColison() {
		// TODO Auto-generated method stub
		if ((ballPosition.getX() <= 0) || (ballPosition.getX() >= (WINDOW_WIDTH) - BALL_RAD)) {
			collisonVertical = true;

		} // Seitenwaende
		if (ballPosition.getY() <= 0) {
			collisonHorizontal = true;

		} // Decke
	}
}
