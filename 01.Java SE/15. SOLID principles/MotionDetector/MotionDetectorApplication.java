package MotionDetector;
import java.util.*;

public class MotionDetectorApplication {

	
	public static void main(String[] args) {
		
		List<AlarmChannel> listOfAllarmChannels = new ArrayList<AlarmChannel>();
		ImageCapturingDevice camera = new Camera();
		
		
		for (int i = 0 ; i < 3; i++){
			TextAlarm a = new TextAlarm("some alarm message");
			listOfAllarmChannels.add(a);
			
			
		}
		MotionDetector md = new MotionDetector(listOfAllarmChannels, camera);
		md.receiveImages();
		
		
		
	}
}
