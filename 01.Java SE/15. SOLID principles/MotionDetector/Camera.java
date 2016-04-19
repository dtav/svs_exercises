package MotionDetector;
import java.util.Scanner;

public class Camera implements ImageCapturingDevice {

	@Override
	public Image readFromInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ICD waiting input...");

		String input = sc.nextLine();
		Image toSend = new Image(input);
		System.out.println("Camera: sent images");

		return toSend;

	}

}
