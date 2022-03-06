package unibook.logic.commands;

import static unibook.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import unibook.model.Model;
import unibook.model.ModelManager;
import unibook.model.UniBook;
import unibook.model.UserPrefs;
import unibook.testutil.TypicalPersons;


public class ClearCommandTest {

    @Test
    public void execute_emptyUniBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUniBook_success() {
        Model model = new ModelManager(TypicalPersons.getTypicalUniBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalUniBook(), new UserPrefs());
        expectedModel.setUniBook(new UniBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
