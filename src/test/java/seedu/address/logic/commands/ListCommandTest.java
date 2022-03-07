package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.OldModel;
import seedu.address.model.OldModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the OldModel) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private OldModel oldModel;
    private OldModel expectedOldModel;

    @BeforeEach
    public void setUp() {
        oldModel = new OldModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedOldModel = new OldModelManager(oldModel.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), oldModel, ListCommand.MESSAGE_SUCCESS, expectedOldModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(oldModel, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), oldModel, ListCommand.MESSAGE_SUCCESS, expectedOldModel);
    }
}
