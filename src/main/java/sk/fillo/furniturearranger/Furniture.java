package sk.fillo.furniturearranger;

public class Furniture {

	private int x;
	private int y;

	private char type;

	private final int width; //cols
	private final int height; // rows
	private char[][] body;

	public Furniture(char type, int x, int y,  char[][] body) {
		this.type = type;
		this.x = x;
		this.y = y;
		height = body.length;
		if (height <= 0) {
			throw new IllegalArgumentException("Furniture height must be positive: " + height);
		}
		width = body[0].length;
		this.body = new char[height][width];
		for (int r = 0; r < height; r++) {
			char[] row = body[r];
			if (row.length != width) {
				throw new IllegalArgumentException("Furniture width must be same for all rows, expected: " + width);
			}
			for (int c = 0; c < width; c ++) {
				this.body[r][c] = body[r][c];
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public char getType() {
		return type;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public char[][] getBody() {
		return body;
	}

	public char getFieldAt(int x, int y) {
		return body[y][x];
	}

}
