package sk.fillo.furniturearranger.models;

import java.util.Arrays;


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

	public char[][] getCells() {
		return cells;
	}

	public char getCellAt(int row, int col) {
		return cells[row][col];
	}

	public void setCellAt(int row, int col, char value) {
		cells[row][col] = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cells);
		result = prime * result + cols;
		result = prime * result + rows;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bitmap other = (Bitmap) obj;
		if (!Arrays.deepEquals(cells, other.cells))
			return false;
		if (cols != other.cols)
			return false;
		if (rows != other.rows)
			return false;
		return true;
	}

}
