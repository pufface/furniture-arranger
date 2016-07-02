package sk.fillo.furniturearranger;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.anyOf;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

	private static final String[] ARGS = new String[] { "-f", "src/test/resources/input1.txt" };
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void testApp() {
		App.main(ARGS);
		assertThat(outContent.toString(), anyOf(containsString("A(0,2) B(1,3)"), containsString("B(1,3) A(0,2)")));
		assertThat(outContent.toString(), anyOf(containsString("A(0,3) B(1,0)"), containsString("B(1,0) A(0,3)")));
	}
}
