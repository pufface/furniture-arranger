package sk.fillo.furniturearranger.models;


public class Room extends Bitmap {

	private static final char EMPTY = '#';

	public Room(char[][] space) {
		super(space);
	}

	public boolean isEmptyAt(int row, int col) {
		if (row < 0 || row >= rows || col < 0 || col >= cols) {
			return false;
		}
		return cells[row][col] == EMPTY;
	}

	public boolean canPlaceTo(Furniture furniture, int row, int col) {
		for (int r = 0; r < furniture.getHeight(); r++) {
			for (int c = 0; c < furniture.getWidth(); c++) {
				if (!isEmptyAt(row + r, col + c) && !furniture.isEmptyAt(r, c)) {
					return false;
				}
			}
		}
		return true;
	}

}
