package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validSortCommand_sortsList() {
        try {
            List<Prefix> prefixes = Arrays.asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_MODULE);
            List<String> orders = Arrays.asList("asc", "asc", "desc", "asc", "asc", "desc");
            String successMessage = SortCommandParser.formatFields(prefixes, orders);
            SortCommand sortCommand = new SortCommand(prefixes, orders, successMessage);

            String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, successMessage);

            ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
            expectedModel.sortPerson(new SortCommand.PersonComparator(prefixes, orders));
            assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        } catch (ParseException pe) {
            assert false;
        }
    }

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

