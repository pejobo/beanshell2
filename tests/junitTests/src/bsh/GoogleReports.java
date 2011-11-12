package bsh;

import org.junit.Test;
import org.junit.runner.RunWith;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static bsh.TestUtil.eval;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(FilteredTestRunner.class)
public class GoogleReports {

	@Test
	public void issue_60() throws Exception {
		final String script =
				"String foo = null;" +
				"if (foo != null && foo.length() > 0) return \"not empty\";" +
				"return \"empty\";";
		final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		scriptEngineManager.registerEngineName("beanshell", new BshScriptEngineFactory());
		final ScriptEngine engine = scriptEngineManager.getEngineByName("beanshell");
		assertNotNull(engine);
		Object result;
		result = engine.eval(script);
		assertEquals("empty", result);
		result = eval(script);
		assertEquals("empty", result);

	}

}
