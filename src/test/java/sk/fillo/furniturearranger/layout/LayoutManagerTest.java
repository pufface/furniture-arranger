package sk.fillo.furniturearranger.layout;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
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
		List<Position[]> layouts = manager.computeLayouts();
		assertThat(layouts.size(), is(12));
	}

	@Test
	public void testLayoutFurnitureStructure() {
		LayoutManager manager = new LayoutManager(room1, Arrays.asList(furniture1A));
		List<Position[]> layouts = manager.computeLayouts();
		assertThat(layouts, hasLayout(manager, furniture1A, 2, 2));
	}

	@Test
	public void testLayoutFurnitureNegative() {
		LayoutManager manager = new LayoutManager(room1, Arrays.asList(furniture1A));
		List<Position[]> layouts = manager.computeLayouts();
		assertThat(layouts, not(hasLayout(manager, furniture1A, 0, 0)));
	}

	@Test
	public void testLatoutManager() {
		LayoutManager manager = new LayoutManager(room1, furnitures1);
		List<Position[]> layouts = manager.computeLayouts();
		assertThat(layouts, hasLayout(manager, furniture1A, 0, 2, furniture1B, 1, 3));
		assertThat(layouts, hasLayout(manager, furniture1A, 0, 3, furniture1B, 1, 0));
	}

	@Test
	public void testLayoutManagerComplete() {
		LayoutManager manager = new LayoutManager(room2, Arrays.asList(furniture2A, furniture2B));
		List<Position[]> layouts = manager.computeLayouts();
		assertThat(layouts.size(), is(4));
		assertThat(layouts, hasLayout(manager, furniture2A, 1, 0, furniture2B, 0, 0));
		assertThat(layouts, hasLayout(manager, furniture2A, 1, 0, furniture2B, 0, 1));
		assertThat(layouts, hasLayout(manager, furniture2A, 0, 0, furniture2B, 2, 0));
		assertThat(layouts, hasLayout(manager, furniture2A, 0, 1, furniture2B, 2, 0));
	}

	private Matcher<List<Position[]>> hasLayout(final LayoutManager manager, final Furniture furniture, final int row, final int col) {
		return new BaseMatcher<List<Position[]>>() {
			@Override
			public boolean matches(Object item) {
				int posIndex = manager.getIntPos(furniture.getType());
				@SuppressWarnings("unchecked")
				final List<Position[]> layouts = (List<Position[]>) item;
				for (Position[] layout : layouts) {
					if (layout[posIndex] != null && layout[posIndex].getRow() == row && layout[posIndex].getCol() == col) {
						return true;
					}
				}
				return false;
			}
			@Override
			public void describeTo(Description description) {
				description.appendText("no match for ").appendText(String.valueOf(furniture.getType()));
			}
		};
	}

	private Matcher<List<Position[]>> hasLayout(final LayoutManager manager, final Furniture furniture1, final int row1, final int col1, final Furniture furniture2, final int row2, final int col2) {
		return new BaseMatcher<List<Position[]>>() {
			@Override
			public boolean matches(Object item) {
				int posIndex1 = manager.getIntPos(furniture1.getType());
				int posIndex2 = manager.getIntPos(furniture2.getType());
				@SuppressWarnings("unchecked")
				final List<Position[]> layouts = (List<Position[]>) item;
				for (Position[] layout : layouts) {
					if (layout[posIndex1] != null && layout[posIndex1].getRow() == row1 && layout[posIndex1].getCol() == col1 && 
							layout[posIndex2] != null && layout[posIndex2].getRow() == row2 && layout[posIndex2].getCol() == col2) {
						return true;
					}
				}
				return false;
			}
			@Override
			public void describeTo(Description description) {
				String type1 = String.valueOf(furniture1.getType());
				String type2 = String.valueOf(furniture2.getType());
				description.appendText("no match for " + type1).appendText(" or for " + type2);
			}
		};
	}
}
