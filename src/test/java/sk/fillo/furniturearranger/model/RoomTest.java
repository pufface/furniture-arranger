package sk.fillo.furniturearranger.model;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Room;
import sk.fillo.furniturearranger.scanner.FurnitureScanner;
import sk.fillo.furniturearranger.scanner.FurnitureScannerTest;
import sk.fillo.furniturearranger.scanner.RoomScanner;
import sk.fillo.furniturearranger.scanner.RoomScannerTest;

public class RoomTest {

	Room room = new RoomScanner(RoomScannerTest.ROOM_1).getRoom();
	Furniture furnitureA = new FurnitureScanner(FurnitureScannerTest.FURNITURE_A).getFurniture();
	Furniture furnitureB = new FurnitureScanner(FurnitureScannerTest.FURNITURE_B).getFurniture();

	@Test
	public void testLayout1Furniture() {
		Room layout1 = room.lay(new Furniture(furnitureA, 2, 0));
		assertThat(layout1, is(not(nullValue())));
		layout1 = layout1.lay(new Furniture(furnitureB, 3, 1));
		assertThat(layout1, is(not(nullValue())));
		assertThat(layout1.getFurnitures().size(), is(2));

		Room layout2 = room.lay(new Furniture(furnitureA, 3, 0));
		assertThat(layout2, is(not(nullValue())));
		layout2 = layout2.lay(new Furniture(furnitureB, 0, 1));
		assertThat(layout2, is(not(nullValue())));
		assertThat(layout2.getFurnitures().size(), is(2));
	}

	@Test
	public void testLayoutFurnitureAStructure() {
		Furniture furniture = new Furniture(furnitureA, 2, 2);
		Room arrageRoom = room.lay(furniture);
		assertThat(arrageRoom, isA(Room.class));
		assertThat(arrageRoom.getFurnitures(), hasItem(furniture));
		assertThat(arrageRoom.getFieldAt(2, 2), is(furniture.getType()));
		assertThat(arrageRoom.getFieldAt(4, 4), is(not(furniture.getType())));
	}

	@Test
	public void testLayoutFurnitureANegative() {
		Room emptyRoom = room.lay(new Furniture(furnitureA, 0, 0));
		assertThat(emptyRoom, is(nullValue()));
	}

}
