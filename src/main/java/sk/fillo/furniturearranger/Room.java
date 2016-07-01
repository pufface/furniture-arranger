package sk.fillo.furniturearranger;

public class Room {

	private final int width;
	private final int height;
	private char[][] space;

	public Room(int width, int height, char[][] space) {
		this.width = width;
		this.height = height;
		this.space = space;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public char[][] getSpace() {
		return space;
	}

	public char getFieldAt(int x, int y) {
		return space[y][x];
	}

	public void print() {
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				System.out.println(space[r][c]);
			}
		}
	}

}
