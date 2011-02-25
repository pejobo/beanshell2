package bsh;

import org.junit.Test;

import java.io.File;

public class Issue_7_Test {

	@Test
	public void run() throws Exception {
		new OldScriptsTest.TestBshScript( new File("tests/test-scripts/class13.bsh")).runTest();
	}

}
