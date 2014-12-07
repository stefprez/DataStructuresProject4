package project;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DriverTest {

	private final ByteArrayOutputStream sysoutContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(sysoutContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void testMain() throws Exception {

		Driver.main(null);

		assertEquals("hello", sysoutContent.toString());

	}

}
