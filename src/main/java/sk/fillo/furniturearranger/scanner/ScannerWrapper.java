package sk.fillo.furniturearranger.scanner;

import java.util.Scanner;

public class ScannerWrapper {

	protected final Scanner scanner;

	public ScannerWrapper(Scanner scanner) {
		if (scanner == null) {
			throw new IllegalArgumentException("Scanner must be povided");
		}
		this.scanner = scanner;
	}

	public ScannerWrapper(String source) {
		if (source == null) {
			throw new IllegalArgumentException("Source must be povided");
		}
		this.scanner = new Scanner(source);
	}

}
