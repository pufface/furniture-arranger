package sk.fillo.furniturearranger.models;

public class Position {

	private final int row;
	private final int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public String getFormatedOutput() {
		return "(" + row + "," + col + ")";
	}

}
