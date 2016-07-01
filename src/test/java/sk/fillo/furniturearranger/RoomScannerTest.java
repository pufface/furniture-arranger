package sk.fillo.furniturearranger;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class RoomScannerTest {

	private static final String EOL = "\n";

	@Test
	public void roomCreation() {
		String roomSource =	"5,6" + EOL
			+ "..###." + EOL 
			+ ".####." + EOL
			+ "######" + EOL
			+ "######" + EOL
			+ "...###";
		RoomScanner roomScanner = new RoomScanner(roomSource);
		Room room = roomScanner.getRoom();
		assertThat(room.getWidth(), is(6));
		assertThat(room.getHeight(), is(5));
		assertThat(room.getFieldAt(0, 0), is('.'));
		assertThat(room.getFieldAt(1, 0), is('.'));
		assertThat(room.getFieldAt(2, 0), is('#'));
		assertThat(room.getFieldAt(3, 0), is('#'));
		assertThat(room.getFieldAt(4, 0), is('#'));
		assertThat(room.getFieldAt(5, 0), is('.'));
		assertThat(room.getFieldAt(5, 4), is('#'));
	}

}
