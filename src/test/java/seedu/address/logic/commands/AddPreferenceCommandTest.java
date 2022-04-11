package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_CATEGORY_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_VALUE_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
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

public class AddPreferenceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPreferenceCommand(null,
                VALID_PREFERENCE_CATEGORY, VALID_PREFERENCE_VALUE));
    }

    @Test
    public void constructor_nullCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPreferenceCommand(INDEX_FIRST_CLIENT, null,
                VALID_PREFERENCE_VALUE));
    }

    @Test
    public void constructor_nullPreference_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPreferenceCommand(INDEX_FIRST_CLIENT,
                VALID_PREFERENCE_CATEGORY, null));
    }

    @Test
    public void execute_invalidClientIndex_failure() {
        String categoryToAdd = VALID_PREFERENCE_CATEGORY;
        String preferenceToAdd = VALID_PREFERENCE_VALUE;
        AddPreferenceCommand addPreferenceCommand = new AddPreferenceCommand(INDEX_INVALID_CLIENT, categoryToAdd,
                preferenceToAdd);
        String expectedMessage = Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandFailure(addPreferenceCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_preferenceAcceptedByModel_success() {
        String categoryToAdd = VALID_PREFERENCE_CATEGORY;
        String preferenceToAdd = VALID_PREFERENCE_VALUE;
        AddPreferenceCommand addPreferenceCommand = new AddPreferenceCommand(INDEX_FIRST_CLIENT, categoryToAdd,
                preferenceToAdd);
        String expectedMessage = String.format(AddPreferenceCommand.MESSAGE_SUCCESS, VALID_PREFERENCE_CATEGORY,
                VALID_PREFERENCE_VALUE);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(addPreferenceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyAddressBook_failure() {
        Model model = new ModelManager();
        AddPreferenceCommand addPreferenceCommand = new AddPreferenceCommand(INDEX_FIRST_CLIENT,
                VALID_PREFERENCE_CATEGORY, VALID_PREFERENCE_VALUE);
        String expectedMessage = String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "add preference to");
        assertCommandFailure(addPreferenceCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        // same values -> returns true
        AddPreferenceCommand addPreferenceCommandToCopy = new AddPreferenceCommand(INDEX_FIRST_CLIENT,
                VALID_PREFERENCE_CATEGORY, VALID_PREFERENCE_VALUE);
        assertTrue(new AddPreferenceCommand(INDEX_FIRST_CLIENT, VALID_PREFERENCE_CATEGORY, VALID_PREFERENCE_VALUE)
                .equals(addPreferenceCommandToCopy));

        // same object -> returns true;
        assertTrue(addPreferenceCommandToCopy.equals(addPreferenceCommandToCopy));

        // null -> returns false
        assertFalse(addPreferenceCommandToCopy.equals(null));

        // different type -> returns false
        assertFalse(addPreferenceCommandToCopy.equals(5));

        // different index -> returns false
        assertFalse(addPreferenceCommandToCopy.equals(new AddPreferenceCommand(INDEX_SECOND_CLIENT,
                VALID_PREFERENCE_CATEGORY, VALID_PREFERENCE_VALUE)));

        // different category -> returns false
        assertFalse(addPreferenceCommandToCopy.equals(new AddPreferenceCommand(INDEX_FIRST_CLIENT,
                VALID_PREFERENCE_CATEGORY_2, VALID_PREFERENCE_VALUE)));

        // different preference -> returns false
        assertFalse(addPreferenceCommandToCopy.equals(new AddPreferenceCommand(INDEX_FIRST_CLIENT,
                VALID_PREFERENCE_CATEGORY, VALID_PREFERENCE_VALUE_2)));
    }

}
