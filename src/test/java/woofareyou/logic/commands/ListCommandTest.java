package woofareyou.logic.commands;

import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.logic.commands.CommandTestUtil.showPetAtIndex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.UserPrefs;
import woofareyou.testutil.TypicalIndexes;
import woofareyou.testutil.TypicalPets;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPets.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPetAtIndex(model, TypicalIndexes.INDEX_FIRST_PET);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
