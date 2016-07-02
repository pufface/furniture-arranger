package sk.fillo.furniturearranger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

	public void printLayoutOutputTo(OutputStream out) throws IOException {
		try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
			for (Room layout : layouts) {
				writer.write(layout.getFormatedOutput());
				writer.write(System.lineSeparator());
			}
		}
	}

	private Set<Room> computeAllPosibleLayouts(Furniture furniture) {
		Set<Room> possibleLayouts = new HashSet<Room>();
		for (Room room : layouts) {
			for (int r = 0; r < room.getHeight(); r++) {
				for (int c = 0; c < room.getWidth(); c++) {
					Furniture fur = new Furniture(furniture, c, r);
					Room nextLayout = room.lay(fur);
					if (nextLayout != null) {
						possibleLayouts.add(nextLayout);
					}
				}
			}
		}
		return possibleLayouts;
	}

}
