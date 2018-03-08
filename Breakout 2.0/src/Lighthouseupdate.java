import java.io.IOException;
import de.cau.infprogoo.lighthouse.LighthouseDisplay;

public class Lighthouseupdate {
	Model model;
	Control control;
	LighthouseDisplay display = new LighthouseDisplay("Jose", "API-TOK_qGHg-HLK1-i1Ji-7AR7-Xy5O");
	private double dividerX = 1000 / 28;
	private double dividerY = 500 / 14;
	private byte[] data = new byte[14 * 28 * 3];

	public void update() {
		// Send data to the display
		try {

			ballUpdate();
			paddleUpdate();
			brickUpdate();
			display.send(data);
			ballRemove();
			paddleRemove();
			brickRemove();
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}

	}

	private void brickRemove() {

		int x = 0;
		int y = 0;
		for (int i = 0; i < model.brickPosition.length; i++) {
			if (model.brickPosition[i] != null && model.activeBricks[i] == false) {
				x = (int) (model.brickPosition[i].getX() / dividerX);
				y = (int) (model.brickPosition[i].getY() / dividerY);

				data[y * 28 * 3 + x * 3 + 0] = (byte) 0;
				data[y * 28 * 3 + x * 3 + 1] = (byte) 0;
				data[y * 28 * 3 + x * 3 + 2] = (byte) 0;
			}
		}

	}

	private void paddleRemove() {

		int x = (int) (model.paddlePosition.getX() / dividerX);
		int y = (int) (model.paddlePosition.getY() / dividerY);

		data[y * 28 * 3 + x * 3 + 0] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 1] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 2] = (byte) 0;

		data[y * 28 * 3 + x * 3 + 3] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 4] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 5] = (byte) 0;

		data[y * 28 * 3 + x * 3 + 6] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 7] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 8] = (byte) 0;

	}

	private void ballRemove() {

		int x = (int) (model.ballPosition.getX() / dividerX);
		int y = (int) (model.ballPosition.getY() / dividerY);
		System.out.println(x + "X " + y + "Y");
		data[y * 28 * 3 + x * 3 + 0] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 1] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 2] = (byte) 0;

		data[y * 28 * 3 + x * 3 + 3] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 4] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 5] = (byte) 0;

		data[y * 28 * 3 + x * 3 + 6] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 7] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 8] = (byte) 0;

	}

	private void brickUpdate() {

		int x = 0;
		int y = 0;
		for (int i = 0; i < model.brickPosition.length; i++) {
			if (model.brickPosition[i] != null) {
				x = (int) (model.brickPosition[i].getX() / dividerX);
				y = (int) (model.brickPosition[i].getY() / dividerY);
				System.out.println(x + "X " + y + "Y");
				if (y == 0) {
					data[y * 28 * 3 + x * 3 + 0] = (byte) 191;
					data[y * 28 * 3 + x * 3 + 1] = (byte) 178;
					data[y * 28 * 3 + x * 3 + 2] = (byte) 65;

					data[y * 28 * 3 + x * 3 + 3] = (byte) 191;
					data[y * 28 * 3 + x * 3 + 4] = (byte) 178;
					data[y * 28 * 3 + x * 3 + 5] = (byte) 65;
				}
				if (y == 1) {
					data[y * 28 * 3 + x * 3 + 0] = (byte) 50;
					data[y * 28 * 3 + x * 3 + 1] = (byte) 147;
					data[y * 28 * 3 + x * 3 + 2] = (byte) 56;

					data[y * 28 * 3 + x * 3 + 3] = (byte) 50;
					data[y * 28 * 3 + x * 3 + 4] = (byte) 147;
					data[y * 28 * 3 + x * 3 + 5] = (byte) 56;
				}
				if (y == 2) {
					data[y * 28 * 3 + x * 3 + 0] = (byte) 36;
					data[y * 28 * 3 + x * 3 + 1] = (byte) 78;
					data[y * 28 * 3 + x * 3 + 2] = (byte) 183;

					data[y * 28 * 3 + x * 3 + 3] = (byte) 36;
					data[y * 28 * 3 + x * 3 + 4] = (byte) 78;
					data[y * 28 * 3 + x * 3 + 5] = (byte) 183;
				} else {
					data[y * 28 * 3 + x * 3 + 0] = (byte) 165;
					data[y * 28 * 3 + x * 3 + 1] = (byte) 21;
					data[y * 28 * 3 + x * 3 + 2] = (byte) 144;

					data[y * 28 * 3 + x * 3 + 3] = (byte) 165;
					data[y * 28 * 3 + x * 3 + 4] = (byte) 21;
					data[y * 28 * 3 + x * 3 + 5] = (byte) 144;
				}
			}
		}

	}

	private void paddleUpdate() {

		int x = (int) (model.paddlePosition.getX() / dividerX);
		int y = (int) (model.paddlePosition.getY() / dividerY);

		data[y * 28 * 3 + x * 3 + 0] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 1] = (byte) 255;
		data[y * 28 * 3 + x * 3 + 2] = (byte) 0;

		data[y * 28 * 3 + x * 3 + 3] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 4] = (byte) 255;
		data[y * 28 * 3 + x * 3 + 5] = (byte) 0;

		data[y * 28 * 3 + x * 3 + 6] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 7] = (byte) 255;
		data[y * 28 * 3 + x * 3 + 8] = (byte) 0;

	}

	private void ballUpdate() {

		int x = (int) (model.ballPosition.getX() / dividerX);
		int y = (int) (model.ballPosition.getY() / dividerY);
		System.out.println(x + "X " + y + "Y");
		data[y * 28 * 3 + x * 3 + 0] = (byte) 255;
		data[y * 28 * 3 + x * 3 + 1] = (byte) 0;
		data[y * 28 * 3 + x * 3 + 2] = (byte) 0;

	}

	public Lighthouseupdate(Model model, Control control) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.control = control;

		// Try connecting to the display
		try {
			display.connect();
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
