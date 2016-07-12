package sk.fillo.furniturearranger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

import sk.fillo.furniturearranger.layout.LayoutManager;
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
		InputStream input = null;
		OutputStream output = null;
		try {
			ArgumentsParser parser = new ArgumentsParser(args);
			input = parser.getInputStream();
			output = parser.getOutputStream();
			Scanner inputScanner = new Scanner(input);

			Room room = new RoomScanner(inputScanner).getRoom();
			List<Furniture> furnitures = new FurnitureScanner(inputScanner).getFurnitures();
			LayoutManager manager = new LayoutManager(room, furnitures);
			manager.computeLayouts();
			manager.printLayoutsOutputTo(output);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeStreams(input, output);
		}
	}

	private static void closeStreams(InputStream input, OutputStream output) {
		if (input != null && input != System.in) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (output != null && output != System.out) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
