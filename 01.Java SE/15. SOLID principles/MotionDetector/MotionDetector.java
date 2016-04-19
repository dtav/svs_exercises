package MotionDetector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MotionDetector {

	ImageCapturingDevice icd;
	private ArrayList<AlarmChannel> acl = new ArrayList<AlarmChannel>();
	Image lastImage;

	public MotionDetector(List<AlarmChannel> listOfAllarmChannels, ImageCapturingDevice icd) {
		for (int i = 0; i <= listOfAllarmChannels.size() -1; i++){
			this.acl.add(listOfAllarmChannels.get(i));
		}
		lastImage = new Image();
		this.icd = icd;
		
	}

	public void sendAlarm(List<AlarmChannel> lac) {
		TextAlarm a = new TextAlarm("Annomaly detected");
		Iterator i = lac.iterator();
		while (i.hasNext()) {
			AlarmChannel ac = (AlarmChannel) i.next();
			ac.Activate();
			System.out.println("Alarm sent");
		}

	}

	boolean firstRun = true;

	
	public void receiveImages() { //ova obratno
		while(true){
			Image i = icd.readFromInput();
		
		if (firstRun) {
			this.lastImage = i;
			firstRun = false;
		} else {
			System.out.println("posledna: " + this.lastImage.getImage());
			System.out.println("nova: " + i.getImage());
			if (i.getImage() == null) {
				System.out.println("Null image received, terminating");
				return;
			}
			
			if (!i.compare(this.lastImage)) {
				System.out.println("MotionDetector: Sending Alarms");
				sendAlarm(this.acl);
			}
		}
		}

	}

//	public void subscribe(AlarmChannel ac) {
//		this.acl.add(ac);
//		System.out.println("Added a channel");
//	}
}
