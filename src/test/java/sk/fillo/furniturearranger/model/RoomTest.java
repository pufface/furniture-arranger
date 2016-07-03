package sk.fillo.furniturearranger.model;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.FurniturePosition;
import sk.fillo.furniturearranger.models.Room;
import sk.fillo.furniturearranger.scanner.FurnitureScanner;
import sk.fillo.furniturearranger.scanner.FurnitureScannerTest;
import sk.fillo.furniturearranger.scanner.RoomScanner;
import sk.fillo.furniturearranger.scanner.RoomScannerTest;

public class RoomTest {

	private Room room1 = new RoomScanner(RoomScannerTest.ROOM_1).getRoom();
	private Room room2 = new RoomScanner(RoomScannerTest.ROOM_2).getRoom();

	private List<Furniture> furnitures = new FurnitureScanner(FurnitureScannerTest.FURNITURES1).getFurnitures();
	private Furniture furnitureA = furnitures.get(0);
	private Furniture furnitureB = furnitures.get(1);

	@Test
	public void testLayoutFurniture() {
		Room layout1 = room1.layToPosition(furnitureA, 0, 2);
		assertThat(layout1, is(not(nullValue())));
		layout1 = layout1.layToPosition(furnitureB, 1, 3);
		assertThat(layout1, is(not(nullValue())));
		assertThat(layout1.getArrangements().size(), is(2));

		Room layout2 = room1.layToPosition(furnitureA, 0, 3);
		assertThat(layout2, is(not(nullValue())));
		layout2 = layout2.layToPosition(furnitureB, 1, 0);
		assertThat(layout2, is(not(nullValue())));
		assertThat(layout2.getArrangements().size(), is(2));
	}

	@Test
	public void testLayoutFurnitureStructure() {
		Room arrageRoom = room1.layToPosition(furnitureA, 2, 2);
		assertThat(arrageRoom, isA(Room.class));
		assertThat(arrageRoom.getArrangements(), hasItem(new FurniturePosition(furnitureA, 2, 2)));
		assertThat(arrageRoom.getFieldAt(2, 2), is(furnitureA.getType()));
		assertThat(arrageRoom.getFieldAt(4, 4), is(not(furnitureA.getType())));
	}

	@Test
	public void testLayoutFurnitureNegative() {
		Room emptyRoom = room1.layToPosition(furnitureA, 0, 0);
		assertThat(emptyRoom, is(nullValue()));
	}

	@Test
	public void testLayoutFurnitureComplete() {
		// TODO:
	}
}
