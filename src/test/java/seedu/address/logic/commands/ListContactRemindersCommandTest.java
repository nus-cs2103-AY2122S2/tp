package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ListContactRemindersCommandTest {

    private Model model;

    @BeforeEach
    public void setModel() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListContactRemindersCommand(null));
    }

    @Test
    public void execute_validIndex_success() {
        Model modelExpected = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person expectedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String messageBody = expectedPerson.getReminderList().toOutputFormat();
        String messageExpected = String.format(ListContactRemindersCommand.MESSAGE_SUCCESS,
                expectedPerson.getName(), messageBody);
        assertCommandSuccess(new ListContactRemindersCommand(INDEX_FIRST_PERSON), model,
                messageExpected, modelExpected);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);

        assertTrue(outOfBoundIndex.getZeroBased() >= model.getAddressBook().getPersonList().size());

        ListContactRemindersCommand listContactRemindersCommand = new ListContactRemindersCommand(outOfBoundIndex);

        assertCommandFailure(listContactRemindersCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
