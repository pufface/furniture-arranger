package sk.fillo.furniturearranger.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import sk.fillo.furniturearranger.models.Furniture;


public class FurnitureScanner extends ScannerWrapper {

	private static final int MIN_REPRESENTATION = 3; // example: "T1#"

	public FurnitureScanner(Scanner scanner) {
		super(scanner);
	}

	public FurnitureScanner(String source) {
		super(source);
	}

	public List<Furniture> getFurnitures() {
		List<Furniture> furnitures = new ArrayList<Furniture>();
		while (scanner.hasNext()) {
			String line = scanner.next();
			Furniture furniture = parseFurniture(line);
			furnitures.add(furniture);
		}
		return furnitures;
	}

	private Furniture parseFurniture(String line) {
		String source = StringUtils.trim(line);
		if (source.length() < MIN_REPRESENTATION) {
			throw new IllegalArgumentException("Invalid furniture representation: " + source);
		}
		char type = source.charAt(0);
		int width = Character.getNumericValue(source.charAt(1));
		int bodyLength = source.length() - 2;
		if (bodyLength % width != 0) {
			throw new IllegalArgumentException("Invalid furniture representation: " + source);
		}
		int height = bodyLength / width;
		char body[][] = new char[height][width];
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				body[r][c] = source.charAt(r*width + c + 2);
			}
		}
		return new Furniture(type, 0,0, body);
	}

}
