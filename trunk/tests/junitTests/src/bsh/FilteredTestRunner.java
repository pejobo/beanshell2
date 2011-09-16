package bsh;

import org.junit.experimental.categories.Category;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FilteredTestRunner extends BlockJUnit4ClassRunner {


	public FilteredTestRunner(final Class<?> klass) throws InitializationError {
		super(klass);
	}


	@Override
	protected List<FrameworkMethod> getChildren() {
		final List<FrameworkMethod> children = super.getChildren();
		if (KnownIssue.SKIP_KOWN_ISSUES) {
			final Iterator<FrameworkMethod> iterator = children.iterator();
			while (iterator.hasNext()) {
				final FrameworkMethod child = iterator.next();
				final Category category = child.getAnnotation(Category.class);
				if (category != null) {
					final Class<?>[] value = category.value();
					if (value != null && Arrays.asList(value).contains(KnownIssue.class)) {
						System.out.println("skipping test " + child.getMethod());
						iterator.remove();
					}
				}
			}
		}
		return children;
	}
}
