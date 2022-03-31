package woofareyou.logic.commands;

import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import woofareyou.model.AddressBook;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.UserPrefs;
import woofareyou.testutil.TypicalPets;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(TypicalPets.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalPets.getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
