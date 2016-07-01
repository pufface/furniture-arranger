package sk.fillo.furniturearranger;

import java.util.Scanner;


public class FurnitureScanner extends ScannerWrapper {

	private static final int MIN_REPRESENTATION = 3; // example: "T1#"

	public FurnitureScanner(Scanner scanner) {
		super(scanner);
	}

	public FurnitureScanner(String source) {
		super(source);
	}

	public Furniture getFurniture() {
		if (!scanner.hasNext()) {
			throw new IllegalArgumentException("Missing first line");
		}
		String source = scanner.next().trim();
		if (source.length() < MIN_REPRESENTATION) {
			throw new IllegalArgumentException("Invalid furniture representation: " + source);
		}
		char type = source.charAt(0);
		char digit = source.charAt(1);
		int width = Character.getNumericValue(digit);
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
