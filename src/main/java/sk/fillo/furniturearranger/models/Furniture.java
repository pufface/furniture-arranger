package sk.fillo.furniturearranger.models;

import java.util.Comparator;

public class Furniture extends Bitmap {

	private static final char EMPTY = '.';

	private char type;

	public Furniture(char type, char[][] bodySource) {
		super(bodySource);
		this.type = type;
	}

	public char getType() {
		return type;
	}

	public boolean isEmptyAt(int row, int col) {
		return cells[row][col] == EMPTY;
	}

	public boolean checkBoundary(int row, int col) {
		if (row < 0 || row >= rows || col < 0 || col >= cols) {
			return false;
		}
		return true;
	}

	// hasCode generated from 'type' field only
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + type;
		return result;
	}

	// equals generated from 'type' field only
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Furniture other = (Furniture) obj;
		if (type != other.type)
			return false;
		return true;
	}

	public static Comparator<Furniture> getSizeComparator() {
		return new Comparator<Furniture>() {
			@Override
			public int compare(Furniture furniture1, Furniture furniture2) {
				int area1 = furniture1.getHeight() * furniture1.getWidth();
				int area2 = furniture2.getHeight() * furniture2.getWidth();
				return area2 - area1;
			}
		};
	}

}
