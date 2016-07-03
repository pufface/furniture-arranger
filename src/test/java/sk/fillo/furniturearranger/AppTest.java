package sk.fillo.furniturearranger;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

	private static final String INPUT_FILE = "src/test/resources/input1.txt";
	private static final String INPUT_FILE_NEGATIVE1 = "src/test/resources/inputNeg1.txt";
	private static final String INPUT_FILE_NEGATIVE2 = "src/test/resources/inputNeg2.txt";
	private static final String OUTPUT_FILE = "output.txt";
	private static final String EXPECTED_OUTPUT1 = "A(0,2) B(1,3)";
	private static final String EXPECTED_OUTPUT1_ALT = "B(1,3) A(0,2)";
	private static final String EXPECTED_OUTPUT2 = "A(0,3) B(1,0)";
	private static final String EXPECTED_OUTPUT2_ALT = "B(1,0) A(0,3)";
	private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setupStreams() throws FileNotFoundException {
		System.setOut(new PrintStream(outContent));
		File input = new File(INPUT_FILE);
		FileInputStream inputStream = new FileInputStream(input);
		System.setIn(inputStream);
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setIn(null);
	}

	@After
	public void cleanOutputFile() {
		File output = new File(OUTPUT_FILE);
		if (output.exists()) {
			output.delete();
		}
	}

	@Test
	public void testAppSystemInSystemOut() {
		App.main(new String[] {});
		assertThat(outContent.toString(), anyOf(containsString(EXPECTED_OUTPUT1), containsString(EXPECTED_OUTPUT1_ALT)));
		assertThat(outContent.toString(), anyOf(containsString(EXPECTED_OUTPUT2), containsString(EXPECTED_OUTPUT2_ALT)));
	}

	@Test
	public void testAppFileInputSystemOut() {
		App.main(new String[] { "-i", INPUT_FILE });
		assertThat(outContent.toString(), anyOf(containsString(EXPECTED_OUTPUT1), containsString(EXPECTED_OUTPUT1_ALT)));
		assertThat(outContent.toString(), anyOf(containsString(EXPECTED_OUTPUT2), containsString(EXPECTED_OUTPUT2_ALT)));
	}

	@Test
	public void testAppFileInputFileOutput() throws IOException {
		App.main(new String[] { "-i", INPUT_FILE, "-o", OUTPUT_FILE });
		File outputFile = new File(OUTPUT_FILE);
		assertThat(outputFile.exists(), is(true));
		String outputFileContent = new String(Files.readAllBytes(outputFile.toPath()));
		assertThat(outputFileContent, anyOf(containsString(EXPECTED_OUTPUT1), containsString(EXPECTED_OUTPUT1_ALT)));
		assertThat(outputFileContent, anyOf(containsString(EXPECTED_OUTPUT2), containsString(EXPECTED_OUTPUT2_ALT)));
	}

	@Test
	public void testAppFileInputFileNegative1() throws IOException {
		App.main(new String[] { "-i", INPUT_FILE_NEGATIVE1});
		assertThat(outContent.toString().length(), is(0));
	}

	@Test
	public void testAppFileInputFileNegative2() throws IOException {
		App.main(new String[] { "-i", INPUT_FILE_NEGATIVE2});
		assertThat(outContent.toString().length(), is(0));
	}

}
