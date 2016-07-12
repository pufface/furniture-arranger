package sk.fillo.furniturearranger.layout;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Position;
import sk.fillo.furniturearranger.scanner.FurnitureScanner;
import sk.fillo.furniturearranger.scanner.FurnitureScannerTest;

public class LayoutTest {

	private List<Furniture> furnitures1 = new FurnitureScanner(FurnitureScannerTest.FURNITURES1).getFurnitures();
	private Furniture furniture1A = furnitures1.get(0);
	private Furniture furniture1B = furnitures1.get(1);

	@Test
	public void testLayout1Furniture() {
		Layout layout1 = new Layout(furniture1A, new Position(0, 2));
		boolean inColision = layout1.colidateWithOthers(furniture1B, 1, 3);
		assertThat(inColision, is(false));
	}

	@Test
	public void testLayout2Furniture() {
		Layout layout1 = new Layout(furniture1A, new Position(0, 3));
		boolean inColision = layout1.colidateWithOthers(furniture1B, 1, 0);
		assertThat(inColision, is(false));
	}

	@Test
	public void testLayoutFurnitureNegative() {
		Layout layout1 = new Layout(furniture1A, new Position(0, 2));
		boolean inColision = layout1.colidateWithOthers(furniture1B, 1, 2);
		assertThat(inColision, is(true));
	}

}
