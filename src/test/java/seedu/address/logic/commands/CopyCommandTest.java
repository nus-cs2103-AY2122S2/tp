package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FormatPersonUtil;
import seedu.address.model.person.Person;

class CopyCommandTest {
    private List<Prefix> partialPrefixes = Arrays.asList(PREFIX_NAME, PREFIX_PHONE,
            PREFIX_EMAIL, PREFIX_ADDRESS);
    private List<Prefix> allPrefixes = Arrays.asList(PREFIX_NAME, PREFIX_PHONE,
            PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_MODULE, PREFIX_COMMENT);
    private FormatPersonUtil fpJson = new FormatPersonUtil(FormatPersonUtil.JSON_FORMAT);
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validCopyCommand_copyPerson() {
        try {
            CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_PERSON, partialPrefixes, fpJson);

            Person personToCopy = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
            String expectedMessage = fpJson.formatPerson(personToCopy, partialPrefixes);

            ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
            assertCommandSuccess(copyCommand, model, expectedMessage, expectedModel);

        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void execute_validCopyCommand_copyAddressBook() {
        try {
            CopyCommand copyCommand = new CopyCommand(allPrefixes, fpJson);

            List<Person> personToCopy = model.getFilteredPersonList();
            String expectedMessage = fpJson.formatAddressBook(personToCopy, allPrefixes);

            ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
            assertCommandSuccess(copyCommand, model, expectedMessage, expectedModel);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void execute_invalidCopyCommand_outOfBound() {
        try {
            assertCommandFailure(new CopyCommand(Index.fromOneBased(1231), partialPrefixes, fpJson),
                    model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void equals() {
        CopyCommand firstCopyCommand = new CopyCommand(INDEX_FIRST_PERSON, partialPrefixes, fpJson);
        CopyCommand secondCopyCommand = new CopyCommand(INDEX_SECOND_PERSON, allPrefixes, fpJson);

        // same object -> returns true
        assertTrue(firstCopyCommand.equals(firstCopyCommand));

        // same values -> returns true
        CopyCommand firstCopyCommandCopy = new CopyCommand(INDEX_FIRST_PERSON, partialPrefixes, fpJson);
        assertTrue(firstCopyCommand.equals(firstCopyCommandCopy));

        // different types -> returns false
        assertFalse(firstCopyCommand.equals(1));

        // null -> returns false
        assertFalse(firstCopyCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCopyCommand.equals(secondCopyCommand));

    }
}
