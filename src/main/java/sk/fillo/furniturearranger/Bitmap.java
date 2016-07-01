package sk.fillo.furniturearranger;


public class Bitmap {

	// Coordinates space: top-left origin
	private final int rows;
	private final int cols;
	private char[][] cells;

	public Bitmap(char[][] body) {
		if (body == null || body.length == 0) {
			rows = 0;
			cols = 0;
		} else {
			rows = body.length;
			cols = body[0].length;
		}
		cells = new char[rows][cols];
		for (int r = 0; r < rows; r++) {
			char[] row = body[r];
			if (row.length != cols) {
				throw new IllegalArgumentException("All rows must have same size, expected: " + cols);
			}
			for (int c = 0; c < cols; c ++) {
				cells[r][c] = body[r][c];
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public char getCellAt(int row, int col) {
		return cells[row][col];
	}

}
