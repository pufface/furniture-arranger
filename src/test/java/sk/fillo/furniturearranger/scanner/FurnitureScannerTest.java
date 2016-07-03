package sk.fillo.furniturearranger.scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import sk.fillo.furniturearranger.App;
import sk.fillo.furniturearranger.models.Furniture;

public class FurnitureScannerTest {

	private static final String EOL = App.EOL;

	public static final String FURNITURES1 = "A2####" + EOL
											+ "B3.#.###.#.";

	public static final String FURNITURES2 = "A2##.#" + EOL
											+ "B1###";

	@Test
	public void furnitureCreationA() {
		FurnitureScanner furnitureScanner = new FurnitureScanner(FURNITURES1);
		List<Furniture> furnitures = furnitureScanner.getFurnitures();
		Furniture furnitureA = furnitures.get(0);
		assertThat(furnitureA.getWidth(), is(2));
		assertThat(furnitureA.getHeight(), is(2));
		assertThat(furnitureA.getType(), is('A'));
		assertThat(furnitureA.getFieldAt(0, 0), is('#'));
		assertThat(furnitureA.getFieldAt(0, 1), is('#'));
		assertThat(furnitureA.getFieldAt(1, 0), is('#'));
		assertThat(furnitureA.getFieldAt(1, 1), is('#'));
	}

	@Test
	public void furnitureCreationB() {
		FurnitureScanner furnitureScanner = new FurnitureScanner(FURNITURES1);
		List<Furniture> furnitures = furnitureScanner.getFurnitures();
		Furniture furnitureB = furnitures.get(1);
		assertThat(furnitureB.getWidth(), is(3));
		assertThat(furnitureB.getHeight(), is(3));
		assertThat(furnitureB.getType(), is('B'));
		assertThat(furnitureB.getFieldAt(0, 0), is('.'));
		assertThat(furnitureB.getFieldAt(1, 0), is('#'));
		assertThat(furnitureB.getFieldAt(2, 0), is('.'));
		assertThat(furnitureB.getFieldAt(1, 1), is('#'));
		assertThat(furnitureB.getFieldAt(2, 2), is('.'));
	}

}
