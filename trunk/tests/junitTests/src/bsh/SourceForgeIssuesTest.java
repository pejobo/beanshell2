package bsh;

import bsh.classpath.ClassManagerImpl;
import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.WeakReference;

public class SourceForgeIssuesTest {

	@Test
	/** <a href="http://sourceforge.net/tracker/?func=detail&aid=2898046&group_id=4075&atid=104075">sourceforge issue 2898046</a> */
	public void sourceforge_issue_2898046() throws Exception {
		final String CODE_2898046 =
				/* 1*/ "import javax.xml.parsers.*;\n"+
				/* 2*/ "import org.xml.sax.InputSource;\n"+
				/* 3*/ "events = new ArrayList();"+
				/* 4*/ "factory = SAXParserFactory.newInstance();\n"+
				/* 5*/ "saxParser = factory.newSAXParser();\n"+
				/* 6*/ "parser = saxParser.getXMLReader();\n"+
				/* 7*/ "parser.setContentHandler( this );\n"+
				/* 8*/ "\n"+
				/* 9*/ "invoke( name, args ) {\n"+
				/*10*/ "	events.add( name );\n"+
				/*11*/ "}\n"+
				/*12*/ "\n"+
				/*13*/ "source = new InputSource(new StringReader(\"<xml>test</xml>\"));\n"+
				/*14*/ "parser.parse( source );" +
				/*15*/ "return events;";
		Assert.assertEquals(
				"[setDocumentLocator, startDocument, startElement, characters, endElement, endDocument]",
				TestUtil.eval(CODE_2898046).toString());
	}


	@Test
	/** <a href="http://sourceforge.net/tracker/?func=detail&aid=2884749&group_id=4075&atid=104075">sourceforge issue "Memory leak with WeakReferences" - ID: 2884749</a> */
	public void sourceforge_issue_2884749() throws Exception {
		final ClassManagerImpl classManager = new ClassManagerImpl();
		final WeakReference<BshClassManager.Listener> weakRef;
		{
			final BshClassManager.Listener listener = new DummyListener(1024 * 1000);
			classManager.addListener(listener);
			weakRef = new WeakReference<BshClassManager.Listener>(listener);
		}
		for (int i = 0; i < 10000; i++) {
			  classManager.addListener(new DummyListener(1024 * 100));
		}
		Assert.assertNull(weakRef.get());
	}


	@Test
	/** <a href="http://sourceforge.net/tracker/?func=detail&aid=2945459&group_id=4075&atid=104075">sourceforge issue "Parsing of long hex literals fails" - ID: 2945459</a> */
	public void sourceforge_issue_2945459() throws Exception {
		Assert.assertEquals(0x0000000001L, TestUtil.eval("long foo = 0x0000000001L;", "return foo"));
	}


	@Test
	/** <a href="http://sourceforge.net/tracker/?func=detail&aid=2562805&group_id=4075&atid=104075">sourceforge issue "Debug fails if called method argument is null" - ID: 2562805</a> */
	public void sourceforge_issue_2562805() throws Exception {
		Interpreter.DEBUG = true;
		TestUtil.eval("System.out.println(null);");
		Interpreter.DEBUG = false;
	}
	

	private static class DummyListener implements BshClassManager.Listener {

		final byte[] _memory;


		public DummyListener(int numBytes) {
			_memory = new byte[numBytes];
		}


		public void classLoaderChanged() {
			// noop
		}
	}
}
