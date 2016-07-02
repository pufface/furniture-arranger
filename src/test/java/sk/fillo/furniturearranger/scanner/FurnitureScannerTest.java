package sk.fillo.furniturearranger.scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import sk.fillo.furniturearranger.models.Furniture;

public class FurnitureScannerTest {

	public static final String FURNITURE_A = "A2##.#";
	public static final String FURNITURE_B = "B3.#.###.#.";

	@Test
	public void furnitureCreationA() {
		FurnitureScanner furnitureScanner = new FurnitureScanner(FURNITURE_A);
		Furniture furniture = furnitureScanner.getFurniture();
		assertThat(furniture.getWidth(), is(2));
		assertThat(furniture.getHeight(), is(2));
		assertThat(furniture.getType(), is('A'));
		assertThat(furniture.getFieldAt(0, 0), is('#'));
		assertThat(furniture.getFieldAt(1, 0), is('#'));
		assertThat(furniture.getFieldAt(0, 1), is('.'));
		assertThat(furniture.getFieldAt(1, 1), is('#'));
	}

	@Test
	public void furnitureCreationB() {
		FurnitureScanner furnitureScanner = new FurnitureScanner(FURNITURE_B);
		Furniture furniture = furnitureScanner.getFurniture();
		assertThat(furniture.getWidth(), is(3));
		assertThat(furniture.getHeight(), is(3));
		assertThat(furniture.getType(), is('B'));
		assertThat(furniture.getFieldAt(0, 0), is('.'));
		assertThat(furniture.getFieldAt(0, 1), is('#'));
		assertThat(furniture.getFieldAt(0, 2), is('.'));
		assertThat(furniture.getFieldAt(1, 1), is('#'));
		assertThat(furniture.getFieldAt(2, 2), is('.'));
	}

}
