package de.befrish.jqwik.vavr;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * @author Benno Müller
 */
public final class TestMatchers {

    private TestMatchers() {
        super();
    }

    public static Matcher<String> hasOnlyAs() {
        return new TypeSafeDiagnosingMatcher<String>() {
            @Override
            protected boolean matchesSafely(final String item, final Description mismatchDescription) {
                mismatchDescription.appendText("String ").appendValue(item);
                return item.replaceAll("a", "").isEmpty();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("String only containing char 'a'");
            }
        };
    }
}
