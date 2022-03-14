package seedu.contax.logic.commands;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.contax.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new Schedule(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, new CommandResult(ListCommand.MESSAGE_SUCCESS,
                GuiListContentType.PERSON), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, new CommandResult(ListCommand.MESSAGE_SUCCESS,
                GuiListContentType.PERSON), expectedModel);
    }
}
