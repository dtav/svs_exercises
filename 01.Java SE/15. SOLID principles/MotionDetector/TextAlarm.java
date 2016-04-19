package MotionDetector;

public class TextAlarm implements AlarmChannel {
	private String contents;

	public TextAlarm(String contents) {
		this.contents = contents;
	}

	public void setContents(String contents) {
		this.contents = contents;

	}
	
	public void showAlarm(){
		System.out.println("Alarm message: "+this.contents);
	}
	
	public String getContents(){
		return this.contents;
	}

	public void Activate() {
		System.out.println("Alarm Activated");
		
	}
}
