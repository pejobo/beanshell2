package bsh;

import org.junit.Assert;
import org.junit.Test;

public class StringLiteralTest {

	private static final String ESCAPE_CHAR = "\\";

	private enum DelimiterMode {
		SINGLE_LINE("\""),

		MULTI_LINE("\"\"\"");

		private final String _delimiter;

		DelimiterMode(String delimiter) {
			_delimiter = delimiter;
		}


		public String delimiter() {
			return _delimiter;
		}
	}


	@Test
	public void parse_string_literal() throws Exception {
		assertStringParsing("test", DelimiterMode.SINGLE_LINE);
	}


	@Test
	public void parse_long_string_literal_singleline() throws Exception {
		assertStringParsing("test", DelimiterMode.MULTI_LINE);
	}


	@Test
	public void parse_string_literal_with_escaped_chars() throws Exception {
		assertStringParsing(
				"\\\n\t\r\"\'",
				ESCAPE_CHAR + '\\' +
				ESCAPE_CHAR + "n" +
				ESCAPE_CHAR + "t" +
				ESCAPE_CHAR + "r" +
				ESCAPE_CHAR + '"' +
				ESCAPE_CHAR + "'",
				DelimiterMode.SINGLE_LINE);
	}


	@Test
	public void parse_string_literal_with_special_chars_multiline() throws Exception {
		assertStringParsing(
				"\t\n\\\"\'",
				"\t\n\\\"\'",
				DelimiterMode.MULTI_LINE);
	}


	/** http://sourceforge.net/tracker/?func=detail&aid=1898217&group_id=4075&atid=104075 */
	@Test
	public void parse_unicode_literals() throws Exception {
		assertStringParsing("\u00FF", "\\u00FF", DelimiterMode.SINGLE_LINE);
	}


	@Test
	public void parse_long_string_literal_multiline() throws Exception {
		assertStringParsing("test\ntest", DelimiterMode.MULTI_LINE);
	}

	@Test
	public void parse_unicode_literals_in_comment() throws Exception {
		final Interpreter interpreter = new Interpreter();
		Object result = interpreter.eval("// source path: C:\\user\\desktop");
		Assert.assertEquals(result, null);
	}

	@Test
	public void parse_unicode_literals_in_apostrophe() throws Exception {
		final Interpreter interpreter = new Interpreter();
		char c = (char) interpreter.eval("return '\\u51ea\'");
		Assert.assertEquals(c, '\u51ea');
	}

	@Test
	public void parse_unicode_literals_in_quotes_and_comment() throws Exception {
		final Interpreter interpreter = new Interpreter();
		interpreter.eval("s = \"\\u51ea1234\"; // source path: C:\\user\\desktop");
		String s = (String) interpreter.eval("s = s + \"\\u51EA3456\"");
		Assert.assertEquals(s, "\u51ea1234\u51EA3456");
	}

	@Test
	public void parse_unicode_literals_return_quotes_and_comment() throws Exception {
		final Interpreter interpreter = new Interpreter();
		String s = (String) interpreter.eval("return \"\\u51EA1234\"; // source path: C:\\user\\desktop");
		Assert.assertEquals(s, "\u51EA1234");
	}

	private void assertStringParsing(final String s, final DelimiterMode mode) throws EvalError {
		assertStringParsing(s, s, mode);
	}


	private void assertStringParsing(final String expected, final String source, final DelimiterMode mode) throws EvalError {
		final Interpreter interpreter = new Interpreter();
		Assert.assertEquals(expected, interpreter.eval("return " + mode.delimiter() + source + mode.delimiter() + ""));
	}

}
