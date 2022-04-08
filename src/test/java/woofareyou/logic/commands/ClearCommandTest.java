package woofareyou.logic.commands;

import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import org.junit.jupiter.api.Test;

import woofareyou.model.PetBook;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalPetBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPetBook(), new UserPrefs());
        expectedModel.setPetBook(new PetBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
