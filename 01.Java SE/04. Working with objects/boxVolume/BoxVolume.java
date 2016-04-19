/*
Exercise: BoxVolume
Create a class with instance variables for height, weight, and depth, making each an 
integer. Create a Java application that uses your new class to create two instances to which 
it then sets values for the attributes, and finally displays the values of the object that has 
the greater volume.
 */

package boxVolume;

public class BoxVolume {

	public static void main(String[] args) {
		Box a = new Box(10, 10, 10);
		Box b = new Box(15, 5, 15);
		Box c = new Box(12, 12, 12);

		// compareTo method will return a Box object
		System.out.println("Volume of a: " + a.getVolume());
		System.out.println("Volume of b: " + b.getVolume());
		System.out.println("The bigger box has these attributes");
		a.comapreTo(b).printAttributes();
		
		System.out.println();
		System.out.println("Volume of b: " + b.getVolume());
		System.out.println("Volume of c: " + c.getVolume());
		System.out.println("The bigger box has these attributes");
		c.comapreTo(b).printAttributes();
	}
}
