package sk.fillo.furniturearranger.scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import sk.fillo.furniturearranger.App;
import sk.fillo.furniturearranger.models.Room;

public class RoomScannerTest {

	private static final String EOL = App.EOL;

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
	public void room1Creation() {
		RoomScanner roomScanner = new RoomScanner(ROOM_1);
		Room room = roomScanner.getRoom();
		assertThat(room.getWidth(), is(6));
		assertThat(room.getHeight(), is(5));
		assertThat(room.getCellAt(0, 0), is('.'));
		assertThat(room.getCellAt(0, 1), is('.'));
		assertThat(room.getCellAt(0, 2), is('#'));
		assertThat(room.getCellAt(0, 3), is('#'));
		assertThat(room.getCellAt(0, 4), is('#'));
		assertThat(room.getCellAt(0, 5), is('.'));
		assertThat(room.getCellAt(4, 5), is('#'));
	}

	@Test
	public void room2Creation() {
		RoomScanner roomScanner = new RoomScanner(ROOM_2);
		Room room = roomScanner.getRoom();
		assertThat(room.getWidth(), is(3));
		assertThat(room.getHeight(), is(3));
		assertThat(room.getCellAt(0, 0), is('#'));
		assertThat(room.getCellAt(0, 1), is('#'));
		assertThat(room.getCellAt(0, 2), is('#'));
		assertThat(room.getCellAt(1, 0), is('#'));
		assertThat(room.getCellAt(1, 1), is('#'));
		assertThat(room.getCellAt(1, 2), is('.'));
		assertThat(room.getCellAt(2, 0), is('#'));
		assertThat(room.getCellAt(2, 1), is('#'));
		assertThat(room.getCellAt(2, 2), is('.'));
	}

}
