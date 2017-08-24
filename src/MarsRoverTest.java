import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MarsRoverTest {

	MarsRover marsRover = new MarsRover();

	@Before
	public void setUp(){

		marsRover.setManager(new RoverManager(5, 5));
	}

	@Test
	public void testLoadManagerValid() {
		String upperRightCoordinates = "5 5";
		assertTrue(marsRover.loadManager(upperRightCoordinates));
	}
	@Test
	public void testLoadManagerIncompleteParameter() {
		String upperRightCoordinates = "123";
		assertFalse(marsRover.loadManager(upperRightCoordinates));
	}
	@Test
	public void testLoadManagerNotNumber() {
		String upperRightCoordinates = "4 g";
		assertFalse(marsRover.loadManager(upperRightCoordinates));
	}



	@Test
	public void testLoadRoverValid() {
		String roverSituation = "1 2 N";
		String roverCommands = "LMLMLMLMM";
		assertTrue(marsRover.loadRover(roverSituation, roverCommands));
	}

	@Test
	public void testLoadRoverInvalidCommands() {
		String roverSituation = "1 2 N";
		String roverCommands = "LML7RH";
		assertFalse(marsRover.loadRover(roverSituation, roverCommands));
	}

	@Test
	public void testLoadRoverInvalidSituation() {
		String roverSituation = "1 2 g";
		String roverCommands = "LMLRM";
		assertFalse(marsRover.loadRover(roverSituation, roverCommands));
	}



}
