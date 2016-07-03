package sk.fillo.furniturearranger.models;

public class FurniturePosition {

	private final Furniture furniture;
	private int row;
	private int col;

	public FurniturePosition(Furniture furniture, int row, int col) {
		this.furniture = furniture;
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return furniture.getType() + "(" + row + "," + col + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result
				+ ((furniture == null) ? 0 : furniture.hashCode());
		result = prime * result + row;
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
		FurniturePosition other = (FurniturePosition) obj;
		if (col != other.col)
			return false;
		if (furniture == null) {
			if (other.furniture != null)
				return false;
		} else if (!furniture.equals(other.furniture))
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}
