package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.parser.ListCommandParser.PREDICATE_SHOW_FLAGGED_PERSONS;
import static seedu.address.logic.parser.ListCommandParser.PREDICATE_SHOW_UNFLAGGED_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalHustleBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHustleBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getHustleBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(PREDICATE_SHOW_ALL_PERSONS), model,
                ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(PREDICATE_SHOW_ALL_PERSONS), model,
                ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsFlagged() {
        ListCommand command = new ListCommand(PREDICATE_SHOW_FLAGGED_PERSONS);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_FLAGGED_PERSONS);
        assertCommandSuccess(command, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(TypicalPersons.getFlaggedPersons(), model.getFilteredPersonList());
    }

    @Test
    public void execute_listIsFiltered_showsUnFlagged() {
        ListCommand command = new ListCommand(PREDICATE_SHOW_UNFLAGGED_PERSONS);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_UNFLAGGED_PERSONS);
        assertCommandSuccess(command, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(TypicalPersons.getUnFlaggedPersons(), model.getFilteredPersonList());
    }
}
