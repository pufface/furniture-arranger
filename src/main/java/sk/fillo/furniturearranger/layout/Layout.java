package sk.fillo.furniturearranger.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Position;

class Layout {

	private final Map<Furniture, Position> arrangements;

	public Layout(Layout layout, Furniture furniture, Position position) {
		arrangements = new HashMap<Furniture, Position>(layout.arrangements);
		arrangements.put(furniture, position);
	}

	public Layout(Furniture furniture, Position position) {
		arrangements = new HashMap<Furniture, Position>();
		arrangements.put(furniture, position);
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

	public boolean colidateWithOthers(Furniture furniture, int row, int col) {
		for (Entry<Furniture, Position> entry : arrangements.entrySet()) {
			Furniture other = entry.getKey();
			Position otherPosition = entry.getValue();
			for (int r = 0; r < furniture.getHeight(); r++) {
				for (int c = 0; c < furniture.getWidth(); c++) {
					int tempRow = row + r - otherPosition.getRow();
					int tempCol = col + c - otherPosition.getCol();
					if (other.checkBoundary(tempRow, tempCol) && !other.isEmptyAt(tempRow, tempCol)
							&& !furniture.isEmptyAt(r, c)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrangements == null) ? 0 : arrangements.hashCode());
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
		Layout other = (Layout) obj;
		if (arrangements == null) {
			if (other.arrangements != null)
				return false;
		} else if (!arrangements.equals(other.arrangements))
			return false;
		return true;
	}

}
