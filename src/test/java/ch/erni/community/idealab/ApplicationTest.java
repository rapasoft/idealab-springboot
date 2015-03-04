package ch.erni.community.idealab;

import org.junit.Test;

public class ApplicationTest {

	@Test
	public void testCanBeRunAsStandalone() throws Exception {
		Application.main(new String[]{"--server.port=12345"});
	}

}