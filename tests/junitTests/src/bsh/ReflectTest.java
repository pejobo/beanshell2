package bsh;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ReflectTest {

    /**
     * See {@link bsh.SourceForgeIssuesTest#sourceforge_issue_2562805()}.
     * This test checks the resolving of PrintStream.println(null). This may be resolved to
     * {@link java.io.PrintStream#println(char[])}, depending on the ordering of the methods when using reflection.
     * This will result in a {@code NullPointerException}.
     */
    @Test
    public void findMostSpecificSignature() {
        final int value = Reflect.findMostSpecificSignature(new Class[]{null}, new Class[][]{
                {Double.TYPE},
                {(new char[0]).getClass()},
                {String.class},
                {Object.class},
                {Boolean.TYPE},
                {Integer.TYPE},
                {Long.TYPE},
                {Character.TYPE},
        });
        assertEquals(3, value);
    }
}