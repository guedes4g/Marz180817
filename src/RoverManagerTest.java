import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RoverManagerTest {
	RoverManager manager;
	
	@Before
	public void setUp(){
		this.manager = new RoverManager(5, 5);
	}

	@Test
	public void testValidatePosition() {
		int x = 7, y = 0;
		assertFalse(this.manager.validatePosition(x, y));

		x = -2;
		assertFalse(this.manager.validatePosition(x, y));

		x = 0;
		y = -3;
		assertFalse(this.manager.validatePosition(x, y));

		y = 9;
		assertFalse(this.manager.validatePosition(x, y));

		x = y = 5;
		assertTrue(this.manager.validatePosition(x, y));
	}

	@Test
	public void testAddRover() {
		int x = 1, y = 2;
		char direction = 'N';
		String commands = "LMLMLMLMM";

		assertTrue(manager.getRovers().isEmpty());

		this.manager.addRover(x, y, direction, commands);
		assertTrue(manager.getRovers().size() == 1);

		x = y = 3;
		direction = 'E';
		commands = "MMRMMRMRRM";

		this.manager.addRover(x, y, direction, commands);
		assertTrue(manager.getRovers().size() == 2);
	}
}
