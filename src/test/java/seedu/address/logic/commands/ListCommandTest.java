package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.logic.commands.ListCommand.MESSAGE_NO_CHANGE_FULL;
import static seedu.address.testutil.TypicalApplications.getTypicalInternApplyMemory;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ListCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternApplyMemory(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternApplyMemory(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(ListCommandParser.SORT_NONE, model, MESSAGE_NO_CHANGE_FULL,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        assertCommandSuccess(new ListCommand(), model, MESSAGE_NO_CHANGE_FULL,
                expectedModel);
    }
}
