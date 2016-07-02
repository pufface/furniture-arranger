package sk.fillo.furniturearranger;

import java.util.List;
import java.util.Scanner;

import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Room;
import sk.fillo.furniturearranger.scanner.FurnitureScanner;
import sk.fillo.furniturearranger.scanner.RoomScanner;

/**
 * Furniture Arranger
 *
 */
public class App {

	public static final String EOL = System.lineSeparator();

	public static void main(String[] args) {
		try {
			ArgumentsParser parser = new ArgumentsParser(args);
			Scanner scanner = parser.getScanner();
			Room room = new RoomScanner(scanner).getRoom();
			List<Furniture> furnitures = new FurnitureScanner(scanner).getFurnitures();
			LayoutManager manager = new LayoutManager(room, furnitures);
			manager.computeLayouts();
			manager.printLayoutOutputTo(System.out);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
