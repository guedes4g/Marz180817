import java.lang.Character;
import java.util.List;
import java.util.ArrayList;

public class RoverManager {
	private List<Rover> rovers;
	private int width;
	private int height;
	
	/**
	 * Constructs the manager to control rovers on the plateau with the given dimensions
	 * @param width the width of the plateau
	 * @param height the height of the plateau
	 */
	public RoverManager(int width, int height) {
		this.rovers = new ArrayList<Rover>();
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Checks if the position with the given coordinates is valid, it is:
	 * - it is inside the board: the coordinates are inside the range controller by the manager;
	 * - it is free: no other rover on it at the moment.
	 * @param x the x coordinate of the position to be validated
	 * @param y the y coordinate of the position to be validated
	 * @return true if the position is valid; false otherwise
	 */
	public boolean validatePosition(int x, int y) {
		// off the board
		if (x > this.width || x < 0)
			return false;
		
		if (y > this.height || y < 0)
			return false;
		
		// another rover
		if (this.isPositionFree(x, y))
			return true;
		
		return false;
	}
	
	/**
	 * Adds a rover to be controlled by the manager.
	 * @param x the x coordinate of the rover's initial position
	 * @param y the y coordinate of the rover's initial position
	 * @param direction the direction the rover is point to; case insensitive
	 * @param commands the String with the commands to be run by the rover with the specified parameters
	 */
	public void addRover(int x, int y, char direction, String commands) {
		Rover rover = new Rover(x, y, Character.toUpperCase(direction));
		rover.setManager(this);
		this.rovers.add(rover);
		
		rover.evaluateCommands(commands.toUpperCase());
	}
	
	/**
	 * Prints the current position and direction of all the rovers managed.
	 */
	public void printAllRovers() {
		for (Rover rover : this.rovers)
			System.out.println(rover.getX() + " " + rover.getY() + " " + rover.getDirection());
	}
	
	/**
	 * Checks if the position with the given coordinates is free,
	 * it is, there is no other rover on it at the moment.
	 * @param x the x coordinate of the position
	 * @param y the y coordinate of the position
	 * @return true if the position is free; false otherwise
	 */
	private boolean isPositionFree(int x, int y) {
		for (Rover r : this.rovers)
			if (r.x == x && r.y == y)
				return false;
		
		return true;
	}

	public List<Rover> getRovers() {
		return (List<Rover>) new ArrayList<Rover>(this.rovers);
	}
}
