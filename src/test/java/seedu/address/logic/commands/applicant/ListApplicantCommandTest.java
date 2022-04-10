package seedu.address.logic.commands.applicant;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.testutil.TypicalHireLah.getTypicalHireLah;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListApplicantCommand.
 */
public class ListApplicantCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHireLah(), new UserPrefs());
        expectedModel = new ModelManager(model.getHireLah(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListApplicantCommand(), model, String.format(ListApplicantCommand.MESSAGE_SUCCESS,
                expectedModel.getFilteredApplicantList().size()), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showApplicantAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListApplicantCommand(), model, String.format(ListApplicantCommand.MESSAGE_SUCCESS,
                expectedModel.getFilteredApplicantList().size()), expectedModel);
    }
}
