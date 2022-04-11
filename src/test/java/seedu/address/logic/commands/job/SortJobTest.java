package seedu.address.logic.commands.job;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_JOB_LIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.job.SortJob.MESSAGE_SORT_JOB_SUCCESS;
import static seedu.address.testutil.TypicalJobs.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class SortJobTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_success() {
        String expectedMessage = String.format(MESSAGE_SORT_JOB_SUCCESS);
        SortJob command = new SortJob();
        expectedModel.sortJob();

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_emptyList_throwsCommandException() {
        model.setAddressBook(new AddressBook());
        assertTrue(model.getFilteredJobList().isEmpty());
        String expectedMessage = String.format(MESSAGE_EMPTY_JOB_LIST);
        SortJob command = new SortJob();
        assertCommandFailure(command, model, expectedMessage);
    }
}
