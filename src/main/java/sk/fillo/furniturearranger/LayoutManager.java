package sk.fillo.furniturearranger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Room;

public class LayoutManager {

	private List<Furniture> furnitures;
	private Set<Room> layouts = new HashSet<Room>();

	public LayoutManager(Room room, List<Furniture> furnitures) {
		this.furnitures = furnitures;
		this.layouts.add(room);
	}

	public Set<Room> computeLayouts() {
		for (Furniture furniture : furnitures) {
			Set<Room> nextLayout = computeAllPosibleLayouts(furniture);
			if (nextLayout.isEmpty()) {
				return new HashSet<Room>();
			}
			layouts = nextLayout;
		}
		return layouts;
	}

	private Set<Room> computeAllPosibleLayouts(Furniture furniture) {
		Set<Room> possibleLayouts = new HashSet<Room>();
		for (Room room : layouts) {
			for (int r = 0; r < room.getHeight(); r++) {
				for (int c = 0; c < room.getWidth(); c++) {
					furniture.setPosition(c, r);
					Room nextLayout = room.lay(furniture);
					if (nextLayout != null) {
						possibleLayouts.add(nextLayout);
					}
				}
			}
		}
		return possibleLayouts;
	}

}
