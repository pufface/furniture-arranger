package sk.fillo.furniturearranger.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Room {

	private static final char EMPTY = '#';

	private final Bitmap floorPlan;
	private final Map<Furniture, Position> arrangements;

	public Room(char[][] space) {
		this.floorPlan = new Bitmap(space);
		arrangements = new HashMap<Furniture, Position>();
	}

	public Room(Room room) {
		floorPlan = new Bitmap(room.floorPlan.getCells());
		arrangements = new HashMap<Furniture, Position>(room.arrangements);
	}

	public int getWidth() {
		return floorPlan.getCols();
	}

	public int getHeight() {
		return floorPlan.getRows();
	}

	public Position getFurniturePosition(Furniture furniture) {
		return arrangements.get(furniture);
	}

	public int countFurnitures() {
		return arrangements.size();
	}

	public char getFieldAt(int row, int col) {
		return floorPlan.getCellAt(row, col);
	}

	public boolean isEmptyAt(int row, int col) {
		if (row < 0 || row >= floorPlan.getRows() || col < 0 || col >= floorPlan.getCols()) {
			return false;
		}
		return getFieldAt(row, col) == EMPTY;
	}

	public Room layToPosition(Furniture furniture, int row, int col) {
		if (!canPlaceToPosition(furniture, row, col)) {
			return null;
		}
		Room room = new Room(this);
		room.addToPosition(furniture, row, col);
		return room;
	}

	public String getFormatedOutput() {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<Furniture, Position>> it = arrangements.entrySet().iterator();
		if (it.hasNext()) {
			Entry<Furniture, Position> entry = it.next();
			sb.append(entry.getKey().getType() + entry.getValue().getFormatedOutput());
		}
		while (it.hasNext()) {
			sb.append(" ");
			Entry<Furniture, Position> entry = it.next();
			sb.append(entry.getKey().getType() + entry.getValue().getFormatedOutput());
		}
		return sb.toString();
	}

	private boolean canPlaceToPosition(Furniture furniture, int row, int col) {
		for (int r = 0; r < furniture.getHeight(); r++) {
			for (int c = 0; c < furniture.getWidth(); c++) {
				if (!isEmptyAt(row + r, col + c) && !furniture.isEmptyAt(r, c)) {
					return false;
				}
			}
		}
		return true;
	}

	private void addToPosition(Furniture furniture, int row, int col) {
		for (int r = 0; r < furniture.getHeight(); r++) {
			for (int c = 0; c < furniture.getWidth(); c++) {
				if (!furniture.isEmptyAt(r, c)) {
					floorPlan.setCellAt(row + r, col + c, furniture.getType());
				}
			}
		}
		arrangements.put(furniture, new Position(row, col));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((floorPlan == null) ? 0 : floorPlan.hashCode());
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
		if (floorPlan == null) {
			if (other.floorPlan != null)
				return false;
		} else if (!floorPlan.equals(other.floorPlan))
			return false;
		return true;
	}

}
