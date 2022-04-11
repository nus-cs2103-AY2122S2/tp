package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PREFERENCE_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_CATEGORY_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_INVALID_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeletePreferenceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullClientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletePreferenceCommand(null,
                VALID_PREFERENCE_CATEGORY));
    }

    @Test
    public void constructor_nullCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletePreferenceCommand(INDEX_FIRST_CLIENT, null));
    }

    @Test
    public void execute_invalidClientIndex_failure() {
        DeletePreferenceCommand deletePreferenceCommand = new DeletePreferenceCommand(INDEX_INVALID_CLIENT,
                VALID_PREFERENCE_CATEGORY);
        String expectedMessage = Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandFailure(deletePreferenceCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_invalidCategory_failure() {
        DeletePreferenceCommand deletePreferenceCommand = new DeletePreferenceCommand(INDEX_FIRST_CLIENT,
                INVALID_PREFERENCE_CATEGORY);
        String expectedMessage = String.format(DeletePreferenceCommand.MESSAGE_INVALID_KEY, INVALID_PREFERENCE_CATEGORY,
                "Alice Pauline");
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandFailure(deletePreferenceCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_preferenceAcceptedByModel_success() {
        DeletePreferenceCommand deletePreferenceCommand = new DeletePreferenceCommand(INDEX_FIRST_CLIENT,
                VALID_PREFERENCE_CATEGORY_2);
        String expectedMessage = String.format(String.format(DeletePreferenceCommand.MESSAGE_SUCCESS, ALICE.getName(),
                VALID_PREFERENCE_CATEGORY_2));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(deletePreferenceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyAddressBook_failure() {
        Model model = new ModelManager();
        DeletePreferenceCommand deletePreferenceCommand = new DeletePreferenceCommand(INDEX_FIRST_CLIENT,
                VALID_PREFERENCE_CATEGORY);
        String expectedMessage = String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "delete preference from");
        assertCommandFailure(deletePreferenceCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        // same values -> returns true
        DeletePreferenceCommand deletePreferenceCommandToCopy = new DeletePreferenceCommand(INDEX_FIRST_CLIENT,
                VALID_PREFERENCE_CATEGORY);
        assertTrue(new DeletePreferenceCommand(INDEX_FIRST_CLIENT, VALID_PREFERENCE_CATEGORY)
                .equals(deletePreferenceCommandToCopy));

        // same object returns true;
        assertTrue(deletePreferenceCommandToCopy.equals(deletePreferenceCommandToCopy));

        // null -> returns false
        assertFalse(deletePreferenceCommandToCopy.equals(null));

        // different type -> returns false
        assertFalse(deletePreferenceCommandToCopy.equals(5));

        // different index -> returns false
        assertFalse(deletePreferenceCommandToCopy.equals(new DeletePreferenceCommand(INDEX_SECOND_CLIENT,
                VALID_PREFERENCE_CATEGORY)));

        // different category -> returns false
        assertFalse(deletePreferenceCommandToCopy.equals(new DeletePreferenceCommand(INDEX_FIRST_CLIENT,
                VALID_PREFERENCE_CATEGORY_2)));
    }


}
