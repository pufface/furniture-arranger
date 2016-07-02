package sk.fillo.furniturearranger.models;

public class Furniture {


	public static final char EMPTY = '.';

	private int x;
	private int y;

	private char type;

	private final Bitmap body;

	public Furniture(char type, int x, int y,  char[][] bodySource) {
		this.type = type;
		this.x = x;
		this.y = y;
		body = new Bitmap(bodySource);
	}

	public Furniture(char type, int x, int y,  Bitmap bitmap) {
		this.type = type;
		this.x = x;
		this.y = y;
		body = bitmap;
	}

	public Furniture(Furniture furniture, int x, int y) {
		type = furniture.type;
		this.x = x;
		this.y = y;
		body = furniture.getBody();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
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

	public Bitmap getBody() {
		return body;
	}

	public char getFieldAt(int x, int y) {
		return body.getCellAt(y, x);
	}

	public boolean isEmptyAt(int x, int y) {
		return getFieldAt(x, y) == EMPTY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + type;
		result = prime * result + x;
		result = prime * result + y;
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
		Furniture other = (Furniture) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (type != other.type)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}


}
