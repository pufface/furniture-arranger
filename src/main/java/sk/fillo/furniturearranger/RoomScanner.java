package sk.fillo.furniturearranger;

import java.util.Scanner;

public class RoomScanner extends ScannerWrapper {

	private static final String DIM_DELIMENTER = ",";

	public RoomScanner(Scanner scanner) {
		super(scanner);
	}

	public RoomScanner(String source) {
		super(source);
	}

	public Room getRoom() {
		if (!scanner.hasNext()) {
			throw new IllegalArgumentException("Missing first line");
		}
		String dimension = scanner.next().trim();
		String[] dimensions = dimension.split(DIM_DELIMENTER);
		if (dimensions.length != 2) {
			throw new IllegalArgumentException("Dimension must be provided in form 'height,width', given: " + dimension);
		}
		int height = Integer.parseInt(dimensions[0]);
		int width = Integer.parseInt(dimensions[1]);
		char[][] space = new char[height][width];
		for (int r=0; r<height; r++) {
			if (!scanner.hasNext()) {
				throw new IllegalArgumentException("No data for row: " + r);
			}
			String row = scanner.next().trim();
			if (row.length() < width) {
				throw new IllegalArgumentException("Insufficient data for row: " + r + ": " + row);
			}
			for (int c=0; c<width; c++) {
				space[r][c] = row.charAt(c);
			}
		}
		return new Room(space);
	}

}
