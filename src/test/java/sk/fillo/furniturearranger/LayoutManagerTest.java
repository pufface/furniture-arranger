package sk.fillo.furniturearranger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
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

	private Room room1 = new RoomScanner(RoomScannerTest.ROOM_1).getRoom();
	private List<Furniture> furnitures1 = new FurnitureScanner(FurnitureScannerTest.FURNITURES1).getFurnitures();
	private Furniture furniture1A = furnitures1.get(0);
	private Furniture furniture1B = furnitures1.get(1);

	private Room room2 = new RoomScanner(RoomScannerTest.ROOM_2).getRoom();
	private List<Furniture> furnitures2 = new FurnitureScanner(FurnitureScannerTest.FURNITURES2).getFurnitures();
	private Furniture furniture2A = furnitures2.get(0);
	private Furniture furniture2B = furnitures2.get(1);

	@Test
	public void testLatoutManager() {
		Room layout1 = room1.layToPosition(furniture1A, 0, 2).layToPosition(furniture1B, 1, 3);
		Room layout2 = room1.layToPosition(furniture1A, 0, 3).layToPosition(furniture1B, 1, 0);
		LayoutManager manager = new LayoutManager(room1, Arrays.asList(furniture1A, furniture1B));
		Set<Room> layouts = manager.computeLayouts();

		assertThat(layouts, hasItem(equalTo(layout1)));
		assertThat(layouts, hasItem(equalTo(layout2)));
	}

	@Test
	public void testLayoutManagerComplete() {
		LayoutManager manager = new LayoutManager(room2, Arrays.asList(furniture2A, furniture2B));
		Set<Room> layouts = manager.computeLayouts();
		Room layout1 = room2.layToPosition(furniture2A, 1, 0).layToPosition(furniture2B, 0, 0);
		Room layout2 = room2.layToPosition(furniture2A, 1, 0).layToPosition(furniture2B, 0, 1);
		Room layout3 = room2.layToPosition(furniture2A, 0, 0).layToPosition(furniture2B, 2, 0);
		Room layout4 = room2.layToPosition(furniture2A, 0, 1).layToPosition(furniture2B, 2, 0);

		assertThat(layouts.size(), is(4));
		assertThat(layouts, hasItem(equalTo(layout1)));
		assertThat(layouts, hasItem(equalTo(layout2)));
		assertThat(layouts, hasItem(equalTo(layout3)));
		assertThat(layouts, hasItem(equalTo(layout4)));
	}

}
