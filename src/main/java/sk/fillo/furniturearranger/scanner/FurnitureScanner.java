package sk.fillo.furniturearranger.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sk.fillo.furniturearranger.models.Furniture;


public class FurnitureScanner extends ScannerWrapper {

	private static final int MIN_REPRESENTATION = 3; // example: "T1#"
	private static final int OFFSET_BODY = 2; // one char for type, one char for digit

	public FurnitureScanner(Scanner scanner) {
		super(scanner);
	}

	public FurnitureScanner(String source) {
		super(source);
	}

	public List<Furniture> getFurnitures() {
		List<Furniture> furnitures = new ArrayList<Furniture>();
		while (scanner.hasNext()) {
			String line = scanner.next().trim();
			if (line.length() == 0 ) {
				break;
			}
			Furniture furniture = parseFurniture(line);
			furnitures.add(furniture);
		}
		return furnitures;
	}

	private Furniture parseFurniture(String line) {
		if (line.length() < MIN_REPRESENTATION) {
			throw new IllegalArgumentException("Invalid furniture representation: " + line);
		}
		char type = line.charAt(0);
		int width = Character.getNumericValue(line.charAt(1));
		int bodyLength = line.length() - OFFSET_BODY;
		if (bodyLength % width != 0) {
			throw new IllegalArgumentException("Invalid furniture representation: " + line);
		}
		int height = bodyLength / width;
		char body[][] = new char[height][width];
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				body[r][c] = line.charAt(r*width + c + OFFSET_BODY);
			}
		}
		return new Furniture(type, body);
	}

}
