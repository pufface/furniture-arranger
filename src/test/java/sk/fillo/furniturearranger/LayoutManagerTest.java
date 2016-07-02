package sk.fillo.furniturearranger;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class LayoutManagerTest {

	@Test
	public void testArranger1() {
		Room room = new RoomScanner(RoomScannerTest.ROOM_1).getRoom();
		Furniture furnitureA = new FurnitureScanner(FurnitureScannerTest.FURNITURE_A).getFurniture();
		Furniture furnitureB = new FurnitureScanner(FurnitureScannerTest.FURNITURE_B).getFurniture();

		Room layout1 = room.lay(new Furniture(furnitureA, 2, 0)).lay(new Furniture(furnitureB, 3, 1));
		Room layout2 = room.lay(new Furniture(furnitureA, 3, 0)).lay(new Furniture(furnitureB, 0, 1));

		LayoutManager manager = new LayoutManager(room, Arrays.asList(furnitureA, furnitureB));
		Set<Room> layouts = manager.computeLayouts();
		assertThat(layouts, hasItem(equalTo(layout1)));
		assertThat(layouts, hasItem(equalTo(layout2)));
	}

}
