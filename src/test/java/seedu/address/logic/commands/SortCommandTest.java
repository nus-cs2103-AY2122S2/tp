package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

class SortCommandTest {

    @Test
    public void equals() {
        List<Prefix> firstPrefixList = Arrays.asList(PREFIX_NAME, PREFIX_STATUS);
        List<Prefix> secondPrefixList = Arrays.asList(PREFIX_NAME, PREFIX_STATUS, PREFIX_EMAIL);
        try {
            SortCommand firstSortCommand = new SortCommand(firstPrefixList,
                    "asc", SortCommandParser.formatFields(firstPrefixList));
            SortCommand secondSortCommand = new SortCommand(secondPrefixList,
                    "desc", SortCommandParser.formatFields(secondPrefixList));

            // same object -> returns true
            assertTrue(firstSortCommand.equals(firstSortCommand));

            // same values -> returns true
            SortCommand firstSortCommandCopy = new SortCommand(firstPrefixList,
                    "asc", SortCommandParser.formatFields(firstPrefixList));
            assertTrue(firstSortCommand.equals(firstSortCommandCopy));

            // different types -> returns false
            assertFalse(firstSortCommand.equals(1));

            // null -> returns false
            assertFalse(firstSortCommand.equals(null));

            // different person -> returns false
            assertFalse(firstSortCommand.equals(secondSortCommand));
        } catch (ParseException pe) {
            assert false;
        }
    }
}
