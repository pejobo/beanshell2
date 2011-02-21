package bsh;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileReader;

/**
 * Run the old test scripts inherited from beanshell.
 * It's not always clear what the test cases do so this will need some more investigations for failing tests.
 */
public class OldScriptsTest {

	public static junit.framework.Test suite() throws Exception {
		final TestSuite suite = new TestSuite();
		final File baseDir = new File("tests/test-scripts");
		addTests(baseDir, suite);
		return suite;
	}


	private static void addTests(File baseDir, TestSuite suite) {
		final File[] files = baseDir.listFiles();
		if (files != null) {
			for (File file : files) {
				final String name = file.getName();
				if (file.isFile() && name.endsWith(".bsh") && ! "TestHarness.bsh".equals(name) && ! "RunAllTests.bsh".equals(name)) {
					suite.addTest(new TestBshScript(file));
				}
			}
		}
	}


	public static class TestBshScript extends TestCase {

		private File _file;

		public TestBshScript(final File file) {
			_file = file;
		}


		@Override
		public String getName() {
			return _file.getName();
		}


		@Override
		public void runTest() throws Exception {
			System.out.println("file is " + _file.getAbsolutePath());
			final Interpreter interpreter = new Interpreter();
			final String path = '\"' + _file.getParentFile().getAbsolutePath().replace('\\', '/') + '\"';
			interpreter.eval("path=" + path + ';');
			interpreter.eval("cd(" + path + ");");
			interpreter.eval(new FileReader(_file));
			assertEquals("test_completed should be true", Boolean.TRUE, interpreter.get("test_completed"));
		}

	}
}
