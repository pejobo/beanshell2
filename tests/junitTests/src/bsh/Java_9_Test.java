package bsh;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(FilteredTestRunner.class)
public class Java_9_Test {

    /**
     * Must be run with JVM arg --illegal-access=deny
     */
    @Test
    public void do_not_access_non_open_methods() throws EvalError {
        String script =
                "import java.net.URL;\n" +
                "url = new URL(\"https://github.com/beanshell\");\n" +
                "urlConnection = url.openConnection();\n" +
                "stream = urlConnection.getInputStream();\n" +
                "stream.close();\n";
        new Interpreter().eval(script);
    }

}
