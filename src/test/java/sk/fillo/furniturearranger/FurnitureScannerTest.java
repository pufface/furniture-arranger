package sk.fillo.furniturearranger;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class FurnitureScannerTest {

	@Test
	public void furnitureCreationA() {
		String furnitureSource = "A2##.#";
		FurnitureScanner furnitureScanner = new FurnitureScanner(furnitureSource);
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
		String furnitureSource = "B3.#.###.#.";
		FurnitureScanner furnitureScanner = new FurnitureScanner(furnitureSource);
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
