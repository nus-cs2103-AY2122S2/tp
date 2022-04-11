package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAssessmentAtIndex;
import static seedu.address.testutil.TypicalAssessments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ListAssessmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAssessmentCommand(), model, ListAssessmentCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAssessmentAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListAssessmentCommand(), model, ListAssessmentCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
