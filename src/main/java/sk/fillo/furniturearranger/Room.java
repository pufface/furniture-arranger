package sk.fillo.furniturearranger;

public class Room {

	private final Bitmap space;

	public Room(char[][] space) {
		this.space = new Bitmap(space);
	}

	public int getWidth() {
		return space.getCols();
	}

	public int getHeight() {
		return space.getRows();
	}

	public char getFieldAt(int x, int y) {
		return space.getCellAt(y, x);
	}

}
