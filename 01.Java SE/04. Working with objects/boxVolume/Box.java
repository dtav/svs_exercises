package boxVolume;

public class Box {
	int height;
	int weight;
	int depth;

	public Box() {
		height = weight = depth = 1;
	}

	public Box(int height, int weight, int depth) {
		this.height = height;
		this.weight = weight;
		this.depth = depth;
	}

	public int getVolume() {
		return this.height * this.weight * this.depth;
	}

	public Box comapreTo(Box otherBox) {
		Box bigger = (this.getVolume() > otherBox.getVolume() ? this : otherBox);
		return bigger;
	}

	public void printAttributes() {
		System.out.println("Height: " + this.height + " Weight: " + this.weight + " Depth: " + this.depth);
	}
}
