package seedu.address.logic.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.GRAB;

import org.junit.jupiter.api.Test;

import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;

public class ApplicationStatusComparatorTest {
    private final ApplicationStatusComparator comparator = new ApplicationStatusComparator();

    @Test
    public void compare_invalidInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> comparator.compare(null, GRAB));
        assertThrows(NullPointerException.class, () -> comparator.compare(GRAB, null));
    }

    @Test
    public void parse_validArgs_returnsInt() {
        final Application application1 = new ApplicationBuilder()
                .withName("Application A").withTags("NOT_APPLIED").build();
        final Application application2 = new ApplicationBuilder()
                .withName("Application B").withTags("APPLIED").build();
        final Application application3 = new ApplicationBuilder()
                .withName("Application C").build();
        final Application application4 = new ApplicationBuilder()
                .withName("Application D").withTags("APPLIED").build();

        // compare
        assertEquals(1, comparator.compare(application1, application2));

        // No status compare
        assertEquals(2, comparator.compare(application3, application2));

        // Same tag compare by name
        assertEquals(-2, comparator.compare(application2, application4));
    }
}
