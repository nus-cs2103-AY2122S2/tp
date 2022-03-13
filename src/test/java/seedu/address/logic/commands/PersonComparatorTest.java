package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SortCommand.PersonComparator;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class PersonComparatorTest {
    @Test
    public void equals() {
        PersonComparator firstPersonComparator = new PersonComparator(Arrays.asList(PREFIX_NAME, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_STATUS), "asc");
        PersonComparator secondPersonComparator = new PersonComparator(Arrays.asList(PREFIX_NAME,
                PREFIX_STATUS), "desc");

        // same object -> returns true
        assertTrue(firstPersonComparator.equals(firstPersonComparator));

        // same values -> returns true
        PersonComparator firstPersonComparatorCopy = new PersonComparator(Arrays.asList(PREFIX_NAME, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_STATUS), "asc");
        assertTrue(firstPersonComparator.equals(firstPersonComparatorCopy));

        // different types -> returns false
        assertFalse(firstPersonComparator.equals(1));

        // null -> returns false
        assertFalse(firstPersonComparator.equals(null));

        // different person -> returns false
        assertFalse(firstPersonComparator.equals(secondPersonComparator));
    }
}
