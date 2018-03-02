package bsh;

import org.junit.Test;

import static bsh.TestUtil.eval;
import static junit.framework.Assert.assertEquals;

public class Issue_94_Test {


    @Test
    public void final_in_method_parameter() throws Exception {
        final Object result = eval(
                "String test(final String text){",
                "   return text;",
                "}",
                "return test(\"abc\");"
        );
        assertEquals("abc", result);
    }

}
