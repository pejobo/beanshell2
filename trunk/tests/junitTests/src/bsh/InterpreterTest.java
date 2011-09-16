package bsh;

import org.junit.Test;

import java.lang.ref.WeakReference;

public class InterpreterTest {

	@Test(timeout = 10000)
	public void check_for_memory_leak() throws Exception {
		final WeakReference<Object> reference = new WeakReference<Object>(new Interpreter().eval("x = new byte[1024 * 2024]; return x;"));
		while (reference.get() != null) {
			System.gc();
			Thread.sleep(100);
		}
	}

}
