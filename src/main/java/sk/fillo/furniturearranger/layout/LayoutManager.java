package sk.fillo.furniturearranger.layout;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sk.fillo.furniturearranger.App;
import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Position;
import sk.fillo.furniturearranger.models.Room;

public class LayoutManager {

	private Room room;
	private Map<Character, Furniture> furnitures = new LinkedHashMap<Character, Furniture>();
	private Map<Character, Integer> charMap = new HashMap<Character, Integer>();
	private Map<Integer, Character> intMap = new HashMap<Integer, Character>();
	private List<Position[]> layouts = new ArrayList<Position[]>();
	private int maxSize;

	public LayoutManager(Room room, List<Furniture> furnitures) {
		Collections.sort(furnitures, Furniture.getSizeComparator());
		maxSize = furnitures.size();
		this.room = room;
		for (int i = 0; i < maxSize; i++) {
			Furniture furniture = furnitures.get(i);
			this.furnitures.put(furniture.getType(), furniture);
			charMap.put(furniture.getType(), i);
			intMap.put(i, furniture.getType());
		}
	}

	public List<Position[]> computeLayouts() {
		Iterator<Entry<Character, Furniture>> it = furnitures.entrySet().iterator();
		if (it.hasNext()) {
			Furniture furniture = it.next().getValue();
			layouts = computeAllPosibleLayouts(furniture);
		}
		while (it.hasNext() && !layouts.isEmpty()) {
			Furniture furniture = it.next().getValue();
			layouts = computeAllPosibleLayouts(layouts, furniture);
		}
		return layouts;
	}

	public int getIntPos(char type) {
		return charMap.get(type);
	}

	public void printLayoutsOutputTo(OutputStream out) throws IOException {
		try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
			for (Position[] layout : layouts) {
				writer.write(getFormatedOutput(layout));
				writer.write(App.EOL);
			}
		}
	}

	private List<Position[]> computeAllPosibleLayouts(Furniture furniture) {
		List<Position[]> possibleLayouts = new ArrayList<Position[]>();
		for (int r = 0; r < room.getHeight(); r++) {
			for (int c = 0; c < room.getWidth(); c++) {
				if (room.canPlaceTo(furniture, r, c)) {
					possibleLayouts.add(createNewLayout(furniture.getType(), room.getPosition(r, c)));
				}
			}
		}
		return possibleLayouts;
	}

	private List<Position[]> computeAllPosibleLayouts(List<Position[]> layouts, Furniture furniture) {
		List<Position[]> possibleLayouts = new ArrayList<Position[]>();
		for (Position[] layout : layouts) {
			for (int r = 0; r < room.getHeight(); r++) {
				for (int c = 0; c < room.getWidth(); c++) {
					if (room.canPlaceTo(furniture, r, c) && !isCollision(layout, furniture, r, c)) {
						possibleLayouts.add(createNewLayout(layout, furniture.getType(), room.getPosition(r, c)));
					}
				}
			}
		}
		return possibleLayouts;
	}

	private boolean isCollision(Position[] layout, Furniture furniture, int row, int col) {
		for (int i = 0; i < maxSize; i++) {
			Position otherPosition = layout[i];
			if (otherPosition != null) {
				Furniture other = furnitures.get(intMap.get(i));
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
		}
		return false;
	}

	private String getFormatedOutput(Position[] layout) {
		StringBuilder sb = new StringBuilder();
		boolean atLeastOne = false;
		for (int i = 0; i < maxSize; i++) {
			Position position = layout[i];
			if (position != null) {
				if (atLeastOne) {
					sb.append(" ");
				} else {
					atLeastOne = true;
				}
				sb.append(intMap.get(i)).append("(")
						.append(position.getRow()).append(",")
						.append(position.getCol()).append(")");
			}
		}
		return sb.toString();
	}

	private Position[] createNewLayout(Position[] layout, char type, Position position) {
		Position[] newLayout = Arrays.copyOf(layout, maxSize);
		int pos = charMap.get(type);
		newLayout[pos] = position;
		return newLayout;
	}

	private Position[] createNewLayout(char type, Position position) {
		Position[] newLayout = new Position[maxSize];
		int pos = charMap.get(type);
		newLayout[pos] = position;
		return newLayout;
	}

}
