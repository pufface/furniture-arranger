package sk.fillo.furniturearranger.scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import sk.fillo.furniturearranger.models.Room;

public class RoomScannerTest {

	private static final String EOL = "\n";

	public static final String ROOM_1 = "5,6" + EOL
										+ "..###." + EOL
										+ ".####." + EOL
										+ "######" + EOL
										+ "######" + EOL
										+ "...###";
	
	public static final String ROOM_2 = "3,3" + EOL
										+ "###" + EOL
										+ "##." + EOL
										+ "##.";

	@Test
	public void roomCreation() {
		RoomScanner roomScanner = new RoomScanner(ROOM_1);
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
