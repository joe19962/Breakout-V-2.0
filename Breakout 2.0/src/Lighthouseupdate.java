import java.io.IOException;

import acm.program.GraphicsProgram;
import de.cau.infprogoo.lighthouse.LighthouseDisplay;

public class Lighthouseupdate extends GraphicsProgram {
	Model model;
	Control control;
	LighthouseDisplay display = new LighthouseDisplay("Jose", "API-TOK_qGHg-HLK1-i1Ji-7AR7-Xy5O");

	public void update() {

		// Send data to the display
		try {
			int ballX = (int) model.ballPosition.getX();
			int ballY = (int) model.ballPosition.getY();

			int paddleX = (int) model.paddlePosition.getX();
			int paddleY = (int) model.paddlePosition.getY();

			int brickX = 0;
			int brickY = 0;
			// This array contains for every window (14 rows, 28 columns) three
			// bytes that define the red, green, and blue component of the color
			// to be shown in that window. See documentation of LighthouseDisplay's
			// send(...) method.
			byte[] data = new byte[14 * 28 * 3];
			if (ballX % 14 == 0 && ballY % 28 == 0) {
				data[ballX / 14 + ballY / 28] = (byte) 255;
				data[(ballX / 14 + ballY / 28) + 1] = (byte) 0;
				data[(ballX / 14 + ballY / 28) + 2] = (byte) 0;
			} // ball
				// Fill array
			if (paddleX % 14 == 0 && paddleY % 28 == 0) {
				data[paddleX / 14 + paddleY / 28] = (byte) 255;
				data[(paddleX / 14 + paddleY / 28) + 1] = (byte) 255;
				data[(paddleX / 14 + paddleY / 28) + 2] = (byte) 255;
			} // paddle
			for (int i = 0; i < model.brickPosition.length; i++) {
				if (model.brickPosition[i] == null) {

				} else {
					brickX = (int) model.brickPosition[i].getX();
					brickY = (int) model.brickPosition[i].getY();
					if (model.activeBricks[i] != false) {
						if (brickX % 14 == 0 && brickY % 28 == 0) {
							data[brickX / 14 + brickY / 28] = (byte) 0;
							data[(brickX / 14 + brickY / 28) + 1] = (byte) 0;
							data[(brickX / 14 + brickY / 28) + 2] = (byte) 255;
						} // paddle
					} else {
						if (brickX % 14 == 0 && brickY % 28 == 0) {
							data[brickX / 14 + brickY / 28] = (byte) 0;
							data[(brickX / 14 + brickY / 28) + 1] = (byte) 0;
							data[(brickX / 14 + brickY / 28) + 2] = (byte) 0;
						}
					}
				}
			}

			display.send(data);
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public Lighthouseupdate(Model model, Control control) {
		// TODO Auto-generated constructor stub
		addKeyListeners(control);
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
