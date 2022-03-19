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
        List<String> firstOrder = Arrays.asList("asc", "asc");
        List<String> secondOrder = Arrays.asList("asc", "asc", "desc");
        try {
            SortCommand firstSortCommand = new SortCommand(firstPrefixList,
                    firstOrder, SortCommandParser.formatFields(firstPrefixList, firstOrder));
            SortCommand secondSortCommand = new SortCommand(secondPrefixList,
                    secondOrder, SortCommandParser.formatFields(secondPrefixList, secondOrder));

            // same object -> returns true
            assertTrue(firstSortCommand.equals(firstSortCommand));

            // same values -> returns true
            SortCommand firstSortCommandCopy = new SortCommand(firstPrefixList,
                    firstOrder, SortCommandParser.formatFields(firstPrefixList, firstOrder));
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
