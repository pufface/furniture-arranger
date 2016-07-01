package sk.fillo.furniturearranger;

public class Furniture {

	private int x;
	private int y;

	private char type;

	private final Bitmap body;

	public Furniture(char type, int x, int y,  char[][] body) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.body = new Bitmap(body);
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
		return body.getCols();
	}

	public int getHeight() {
		return body.getRows();
	}

	public char getFieldAt(int x, int y) {
		return body.getCellAt(y, x);
	}

}
