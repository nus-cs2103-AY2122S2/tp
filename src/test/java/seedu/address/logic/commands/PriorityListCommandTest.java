package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code PriorityListCommand}.
 */
class PriorityListCommandTest {
    private Model model;
    private Model expectedModel;
    private PriorityListCommand command = new PriorityListCommand();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_priority_list() {
        expectedModel.sortByPriority();
        assertCommandSuccess(command, model, PriorityListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
