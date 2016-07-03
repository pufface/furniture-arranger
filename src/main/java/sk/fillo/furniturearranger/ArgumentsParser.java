package sk.fillo.furniturearranger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;

public class ArgumentsParser {

	public static final String INPUT_FILE = "-i";
	public static final String OUTPUF_FILE = "-o";

	private String inputFile;
	private String outputFile;

	public ArgumentsParser(String[] args) {
		parse(args);
	}

	private void parse(String[] args) {
		Iterator<String> it = Arrays.asList(args).iterator();
		while (it.hasNext()) {
			String arg = it.next();
			if (INPUT_FILE.equalsIgnoreCase(arg) && it.hasNext()) {
				inputFile = it.next();
			} else if (OUTPUF_FILE.equalsIgnoreCase(arg) && it.hasNext()) {
				outputFile = it.next();
			}
		}
	}

	public InputStream getInputStream() throws FileNotFoundException {
		if (inputFile != null) {
			File file = new File(inputFile);
			if (!file.exists()) {
				throw new IllegalArgumentException("Can not open file: "
						+ file.getAbsolutePath());
			}
			return new FileInputStream(file);
		} else {
			return System.in;
		}
	}

	public OutputStream getOutputStream() throws IOException {
		if (outputFile != null) {
			File file = new File(outputFile);
			if (!file.exists()) {
				file.createNewFile();
			} 
			return new FileOutputStream(file);
		} else {
			return System.out;
		}
	}

}
