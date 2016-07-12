package sk.fillo.furniturearranger.layout;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import sk.fillo.furniturearranger.App;
import sk.fillo.furniturearranger.models.Furniture;
import sk.fillo.furniturearranger.models.Room;

public class LayoutManager {

	private List<Furniture> furnitures;
	private Room room;
	private List<Layout> layouts = new ArrayList<Layout>();

	public LayoutManager(Room room, List<Furniture> furnitures) {
		this.furnitures = furnitures;
		this.room = room;
	}

	public List<Layout> computeLayouts() {
		Collections.sort(furnitures, Furniture.getSizeComparator());
		Iterator<Furniture> it = furnitures.iterator();
		if (it.hasNext()) {
			layouts = computeAllPosibleLayouts(it.next());
		}
		while (it.hasNext() && !layouts.isEmpty()) {
			layouts = computeAllPosibleLayouts(layouts, it.next());
		}
		return layouts;
	}

	public void printLayoutsOutputTo(OutputStream out) throws IOException {
		try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
			for (Layout layout : layouts) {
				writer.write(layout.getFormatedOutput());
				writer.write(App.EOL);
			}
		}
	}

	private List<Layout> computeAllPosibleLayouts(Furniture furniture) {
		List<Layout> possibleLayouts = new ArrayList<Layout>();
		for (int r = 0; r < room.getHeight(); r++) {
			for (int c = 0; c < room.getWidth(); c++) {
				if (room.canPlaceTo(furniture, r, c)) {
					possibleLayouts.add(new Layout(furniture, room.getPosition(r, c)));
				}
			}
		}
		return possibleLayouts;
	}

	private List<Layout> computeAllPosibleLayouts(List<Layout> layouts, Furniture furniture) {
		List<Layout> possibleLayouts = new ArrayList<Layout>();
		for (Layout layout : layouts) {
			for (int r = 0; r < room.getHeight(); r++) {
				for (int c = 0; c < room.getWidth(); c++) {
					if (room.canPlaceTo(furniture, r, c) && !layout.colidateWithOthers(furniture, r, c)) {
						possibleLayouts.add(new Layout(layout, furniture, room.getPosition(r, c)));
					}
				}
			}
		}
		return possibleLayouts;
	}

}
