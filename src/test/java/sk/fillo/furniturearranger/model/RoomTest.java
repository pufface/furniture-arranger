package sk.fillo.furniturearranger.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Room;
import sk.fillo.furniturearranger.scanner.FurnitureScanner;
import sk.fillo.furniturearranger.scanner.FurnitureScannerTest;
import sk.fillo.furniturearranger.scanner.RoomScanner;
import sk.fillo.furniturearranger.scanner.RoomScannerTest;

public class RoomTest {

	private Room room1 = new RoomScanner(RoomScannerTest.ROOM_1).getRoom();
	private List<Furniture> furnitures1 = new FurnitureScanner(FurnitureScannerTest.FURNITURES1).getFurnitures();
	private Furniture furniture1A = furnitures1.get(0);
	private Furniture furniture1B = furnitures1.get(1);

	@Test
	public void testLayoutFurniture() {
		Room layout1 = room1.layToPosition(furniture1A, 0, 2);
		assertThat(layout1, is(not(nullValue())));
		layout1 = layout1.layToPosition(furniture1B, 1, 3);
		assertThat(layout1, is(not(nullValue())));
		assertThat(layout1.countFurnitures(), is(2));

		Room layout2 = room1.layToPosition(furniture1A, 0, 3);
		assertThat(layout2, is(not(nullValue())));
		layout2 = layout2.layToPosition(furniture1B, 1, 0);
		assertThat(layout2, is(not(nullValue())));
		assertThat(layout2.countFurnitures(), is(2));
	}

	@Test
	public void testLayoutFurnitureStructure() {
		Room arrageRoom = room1.layToPosition(furniture1A, 2, 2);
		assertThat(arrageRoom, isA(Room.class));
		assertThat(arrageRoom.getFurniturePosition(furniture1A).getFormatedOutput(), is("(2,2)"));
		assertThat(arrageRoom.getFieldAt(2, 2), is(furniture1A.getType()));
		assertThat(arrageRoom.getFieldAt(4, 4), is(not(furniture1A.getType())));
	}

	@Test
	public void testLayoutFurnitureNegative() {
		Room emptyRoom = room1.layToPosition(furniture1A, 0, 0);
		assertThat(emptyRoom, is(nullValue()));
	}
}
