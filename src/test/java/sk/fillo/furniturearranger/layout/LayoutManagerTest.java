package sk.fillo.furniturearranger.layout;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Position;
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
	public void testLayoutFurniture() {
		LayoutManager manager = new LayoutManager(room1, Arrays.asList(furniture1A));
		List<Layout> layouts = manager.computeLayouts();
		assertThat(layouts.size(), is(12));
	}

	@Test
	public void testLayoutFurnitureStructure() {
		LayoutManager manager = new LayoutManager(room1, Arrays.asList(furniture1A));
		List<Layout> layouts = manager.computeLayouts();
		assertThat(layouts, hasItem(new Layout(furniture1A, new Position(2, 2))));
	}

	@Test
	public void testLayoutFurnitureNegative() {
		LayoutManager manager = new LayoutManager(room1, Arrays.asList(furniture1A));
		List<Layout> layouts = manager.computeLayouts();
		assertThat(layouts, hasItem(not(new Layout(furniture1A, new Position(0, 0)))));
	}

	@Test
	public void testLatoutManager() {
		LayoutManager manager = new LayoutManager(room1, furnitures1);
		List<Layout> layouts = manager.computeLayouts();
		Layout example1 = new Layout(new Layout(furniture1A, new Position(0, 2)), furniture1B, new Position(1, 3));
		Layout example2 = new Layout(new Layout(furniture1A, new Position(0, 3)), furniture1B, new Position(1, 0));
		assertThat(layouts, hasItem(example1));
		assertThat(layouts, hasItem(example2));
	}

	@Test
	public void testLayoutManagerComplete() {
		LayoutManager manager = new LayoutManager(room2, Arrays.asList(furniture2A, furniture2B));
		List<Layout> layouts = manager.computeLayouts();
		Layout layout1 = new Layout(new Layout(furniture2A, new Position(1, 0)), furniture2B, new Position(0, 0));
		Layout layout2 = new Layout(new Layout(furniture2A, new Position(1, 0)), furniture2B, new Position(0, 1));
		Layout layout3 = new Layout(new Layout(furniture2A, new Position(0, 0)), furniture2B, new Position(2, 0));
		Layout layout4 = new Layout(new Layout(furniture2A, new Position(0, 1)), furniture2B, new Position(2, 0));
		assertThat(layouts.size(), is(4));
		assertThat(layouts, hasItem(layout1));
		assertThat(layouts, hasItem(layout2));
		assertThat(layouts, hasItem(layout3));
		assertThat(layouts, hasItem(layout4));
	}

}
