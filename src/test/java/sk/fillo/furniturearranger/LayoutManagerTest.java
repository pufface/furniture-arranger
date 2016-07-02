package sk.fillo.furniturearranger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Room;
import sk.fillo.furniturearranger.scanner.FurnitureScanner;
import sk.fillo.furniturearranger.scanner.FurnitureScannerTest;
import sk.fillo.furniturearranger.scanner.RoomScanner;
import sk.fillo.furniturearranger.scanner.RoomScannerTest;

public class LayoutManagerTest {

	Room room = new RoomScanner(RoomScannerTest.ROOM_1).getRoom();
	List<Furniture> furnitures = new FurnitureScanner(FurnitureScannerTest.FURNITURES).getFurnitures();
	Furniture furnitureA = furnitures.get(0);
	Furniture furnitureB = furnitures.get(1);

	@Test
	public void testArranger1() {
		Room layout1 = room.lay(new Furniture(furnitureA, 2, 0)).lay(new Furniture(furnitureB, 3, 1));
		Room layout2 = room.lay(new Furniture(furnitureA, 3, 0)).lay(new Furniture(furnitureB, 0, 1));

		LayoutManager manager = new LayoutManager(room, Arrays.asList(furnitureA, furnitureB));
		Set<Room> layouts = manager.computeLayouts();
		assertThat(layouts, hasItem(equalTo(layout1)));
		assertThat(layouts, hasItem(equalTo(layout2)));
	}

}
