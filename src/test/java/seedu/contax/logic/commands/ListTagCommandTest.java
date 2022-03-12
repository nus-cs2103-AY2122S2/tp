package seedu.contax.logic.commands;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.commons.core.GuiListContentType;

/**
 * Contains integration tests (interaction with Model) and units tests for ListTagCommand
 */
public class ListTagCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new Schedule(), new UserPrefs());
    }

    @Test
    public void execute_valid_listAllTags() {
        assertCommandSuccess(new ListTagCommand(), model, new CommandResult(ListTagCommand.MESSAGE_SUCCESS,
                GuiListContentType.TAG), expectedModel);
    }
}
