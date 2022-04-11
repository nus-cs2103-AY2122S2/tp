package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertPersonCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookPersons;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertPersonCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBookPersons(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBookPersons(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertPersonCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
