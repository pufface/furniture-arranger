package sk.fillo.furniturearranger.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Room {

	private static final char EMPTY = '#';

	private final Bitmap space;
	private final Set<Furniture> furnitures;

	public Room(char[][] space) {
		this.space = new Bitmap(space);
		furnitures = new HashSet<Furniture>();
	}

	public Room(Room room) {
		space = new Bitmap(room.space.getCells());
		furnitures = new HashSet<Furniture>(room.furnitures);
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

	public boolean isEmptyAt(int x, int y) {
		if (x < 0 || x >= space.getCols() || y < 0 || y >= space.getRows()) {
			return false;
		}
		return getFieldAt(x, y) == EMPTY;
	}

	public Set<Furniture> getFurnitures() {
		return furnitures;
	}

	public Room lay(Furniture furniture) {
		if (!canHold(furniture)) {
			return null;
		}
		Room room = new Room(this);
		room.add(furniture);
		return room;
	}

	public String getFormatedOutput() {
		StringBuilder sb = new StringBuilder();
		Iterator<Furniture> it = furnitures.iterator();
		if (it.hasNext()) {
			sb.append(it.next());
		}
		while (it.hasNext()) {
			sb.append(" ");
			sb.append(it.next());
		}
		return sb.toString();
	}

	private boolean canHold(Furniture furniture) {
		for (int r = 0; r < furniture.getHeight(); r++) {
			for (int c = 0; c < furniture.getWidth(); c++) {
				int roomPosX = furniture.getX() + c;
				int roomPosY = furniture.getY() + r;
				if (!isEmptyAt(roomPosX, roomPosY) && !furniture.isEmptyAt(c, r)) {
					return false;
				}
			}
		}
		return true;
	}

	private void add(Furniture furniture) {
		for (int r = 0; r < furniture.getHeight(); r++) {
			for (int c = 0; c < furniture.getWidth(); c++) {
				if (!furniture.isEmptyAt(c, r)) {
					space.setCellAt(furniture.getY() + r, furniture.getX() + c, furniture.getType());
				}
			}
		}
		furnitures.add(furniture);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((space == null) ? 0 : space.hashCode());
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
		Room other = (Room) obj;
		if (space == null) {
			if (other.space != null)
				return false;
		} else if (!space.equals(other.space))
			return false;
		return true;
	}

}
