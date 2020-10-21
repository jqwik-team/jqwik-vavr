package de.befrish.jqwik.vavr;

import io.vavr.control.Validation;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.CharRange;
import net.jqwik.api.constraints.IntRange;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

class VavrValidationArbitraryTest {

    @Provide
    Arbitrary<Validation<String, Integer>> integersMin3OrAString() {
        return VavrArbitraries.validation(
                Arbitraries.strings().withCharRange('a', 'a'),
                Arbitraries.integers().greaterOrEqual(3));
    }

    @Property
    void generateFrom(@ForAll @From("integersMin3OrAString") final Validation<String, Integer> validation) {
        if (validation.isValid()) {
            assertThat(validation.get(), is(greaterThanOrEqualTo(3)));
        } else {
            assertThat(validation.getError(), hasOnlyAs());
        }
    }

    @Property
    void generate(@ForAll final Validation<String, Integer> validation) {
        // Test that the ArbitraryProvider works (fails if not found)
        assertThat(validation, is(notNullValue()));
    }

    @Property
    void generateWithValueContraints(@ForAll final Validation<@CharRange(from = 'a', to = 'a') String, @IntRange(min = 3, max = 42) Integer> validation) {
        if (validation.isValid()) {
            assertThat(validation.get(), is(greaterThanOrEqualTo(3)));
            assertThat(validation.get(), is(lessThanOrEqualTo(42)));
        } else {
            assertThat(validation.getError(), is(hasOnlyAs()));
        }
    }

    private static Matcher<String> hasOnlyAs() {
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
