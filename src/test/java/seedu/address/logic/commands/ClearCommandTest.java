package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.*;
import seedu.address.model.OldModel;
import seedu.address.model.OldModelManager;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        OldModel oldModel = new OldModelManager();
        OldModel expectedOldModel = new OldModelManager();

        assertCommandSuccess(new ClearCommand(), oldModel, ClearCommand.MESSAGE_SUCCESS, expectedOldModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        OldModel oldModel = new OldModelManager(getTypicalAddressBook(), new UserPrefs());
        OldModel expectedOldModel = new OldModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedOldModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), oldModel, ClearCommand.MESSAGE_SUCCESS, expectedOldModel);
    }

}
