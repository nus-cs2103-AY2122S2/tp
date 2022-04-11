package seedu.address.logic.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.GRAB;

import org.junit.jupiter.api.Test;

import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;


public class NameComparatorTest {
    private static final NameComparator comparator = new NameComparator();

    @Test
    public void compare_invalidInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> comparator.compare(null, GRAB));
        assertThrows(NullPointerException.class, () -> comparator.compare(GRAB, null));
    }

    @Test
    public void parse_validArgs_returnsInt() {
        final Application application1 = new ApplicationBuilder()
                .withName("Application A").withName("Alpha").build();
        final Application application2 = new ApplicationBuilder()
                .withName("Application B").withName("Beta").build();
        final Application application3 = new ApplicationBuilder()
                .withName("Application C").withName("alpha").build();

        // compare
        assertEquals(-1, comparator.compare(application1, application2));
        assertEquals(1, comparator.compare(application2, application1));

        // casing do not matter
        assertEquals(-1, comparator.compare(application3, application2));

        // same text different case
        assertEquals(0, comparator.compare(application1, application3));
    }
}
