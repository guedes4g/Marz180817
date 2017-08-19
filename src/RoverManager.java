import java.util.List;
import java.util.ArrayList;

public class RoverManager {
	private List<Rover> rovers;
	private int width;
	private int height;
	
	public RoverManager(int width, int height) {
		this.rovers = new ArrayList<Rover>();
		this.width = width;
		this.height = height;
	}
	
	public boolean validatePosition(int x, int y) {
		// off the board
		if (x > this.width - 1 || x < 0)
			return false;
		
		if (y > this.height - 1 || x < 0)
			return false;
		
		// another rover
		if (this.isPositionFree(x, y))
			return true;
		
		return false;
	}
	
	public void addRover(int x, int y, char direction, String commands) {
		Rover rover = new Rover(x, y, direction);
		this.rovers.add(rover);
		
		rover.evaluateCommands(commands);
	}
	
	private boolean isPositionFree(int x, int y) {
		for (Rover r : this.rovers) {
			if (r.x == x && r.y == y)
				return false;
		}
		
		return true;
	}
}
