package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SortCommandTest {

    @Test
    public void equals() {
        SortCommand firstSortCommand = new SortCommand(Arrays.asList(PREFIX_NAME,
                PREFIX_STATUS), "asc");
        SortCommand secondSortCommand = new SortCommand(Arrays.asList(PREFIX_NAME,
                PREFIX_ADDRESS, PREFIX_EMAIL), "desc");

        // same object -> returns true
        assertTrue(firstSortCommand.equals(firstSortCommand));

        // same values -> returns true
        SortCommand firstSortCommandCopy = new SortCommand(Arrays.asList(PREFIX_NAME,
                PREFIX_STATUS), "asc");
        assertTrue(firstSortCommand.equals(firstSortCommandCopy));

        // different types -> returns false
        assertFalse(firstSortCommand.equals(1));

        // null -> returns false
        assertFalse(firstSortCommand.equals(null));

        // different person -> returns false
        assertFalse(firstSortCommand.equals(secondSortCommand));
    }
}
