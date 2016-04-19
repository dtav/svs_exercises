package MotionDetector;

public class Image {
	private String img;

	public Image(String img) {
		this.img = img;
	}
	
	public Image(){
		this.img = null;
	}

	public String getImage() {
		return this.img;
	}

	public void setImage(String img) {
		this.img = img;
	}
	
	public boolean compare(Image i){
		if (i.getImage().equals(this.img)){
			return true;
		} else {
			return false;
		}
	}
}
