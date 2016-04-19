package volcanoRobot;

public class VolcanoApplication {

	public static void main(String[] args) {

		VolcanoRobot dante = new VolcanoRobot();
		dante.status = "exploring";
		dante.speed = 2;
		dante.temperature = 510;
		dante.showAttributes();

		System.out.println("Increasing speed to 3...");
		dante.speed = 3;
		dante.showAttributes();

		System.out.println("Changing temperature to 670...");
		dante.temperature = 670;
		dante.showAttributes();

		System.out.println("Checking the temperature...");
		dante.checkTemperature();
		dante.showAttributes();

		/*
		 * Exercise: VolcanoRobotVirgil In the main() method of the
		 * VolcanoApplication class, create a second VolcanoRobot robot named
		 * virgil, set up its instance variables, and display them.
		 */

		VolcanoRobot virgil = new VolcanoRobot();
		virgil.status = "digging";
		virgil.speed = 5;
		virgil.temperature = 650;
		virgil.checkTemperature();
		virgil.showAttributes();
	}
}
