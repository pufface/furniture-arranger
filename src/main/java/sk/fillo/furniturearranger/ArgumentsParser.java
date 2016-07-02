package sk.fillo.furniturearranger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class ArgumentsParser {

	public static final String ARG = "-f";

	private String inputFile;

	public ArgumentsParser(String[] args) {
		parse(args);
	}

	private void parse(String[] args) {
		Iterator<String> it = Arrays.asList(args).iterator();
		while (it.hasNext()) {
			String arg = it.next();
			if (ARG.equalsIgnoreCase(arg) && it.hasNext()) {
				inputFile = it.next();
			}
		}
		if (inputFile == null) {
			throw new IllegalArgumentException("Arguments must be given in form: \"-f pathToFile\"");
		}
	}

	public Scanner getScanner() throws FileNotFoundException {
		File file = new File(inputFile);
		if (!file.exists()) {
			throw new IllegalArgumentException("Can not open file: " + file.getAbsolutePath());
		}
		return new Scanner(file);
	}

}
