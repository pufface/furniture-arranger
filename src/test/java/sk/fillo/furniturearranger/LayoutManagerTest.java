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

	private Room room = new RoomScanner(RoomScannerTest.ROOM_1).getRoom();
	private List<Furniture> furnitures = new FurnitureScanner(FurnitureScannerTest.FURNITURES1).getFurnitures();
	private Furniture furnitureA = furnitures.get(0);
	private Furniture furnitureB = furnitures.get(1);

	@Test
	public void testArranger1() {
		Room layout1 = room.layToPosition(furnitureA, 0, 2).layToPosition(furnitureB, 1, 3);
		Room layout2 = room.layToPosition(furnitureA, 0, 3).layToPosition(furnitureB, 1, 0);

		LayoutManager manager = new LayoutManager(room, Arrays.asList(furnitureA, furnitureB));
		Set<Room> layouts = manager.computeLayouts();
		assertThat(layouts, hasItem(equalTo(layout1)));
		assertThat(layouts, hasItem(equalTo(layout2)));
	}

}
