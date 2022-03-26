package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCandidates.getTypicalAddressBook;
import static seedu.address.testutil.TypicalInterviews.getTypicalInterviewSchedule;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.InterviewSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.predicate.AllWithinTimePeriodPredicate;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ViewCommandTest {

    private Model model;
    private Model expectedModel;
    private AllWithinTimePeriodPredicate predicate;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalInterviewSchedule(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalInterviewSchedule(), new UserPrefs());
        predicate = new AllWithinTimePeriodPredicate(LocalDateTime.now());
    }

    @Test
    public void execute_view_success() {
        expectedModel.updateFilteredInterviewSchedule(predicate);
        assertCommandSuccess(new ViewCommand(predicate), model,
                new CommandResult(String.format(Messages.MESSAGE_INTERVIEWS_LISTED_OVERVIEW,
                        model.getFilteredInterviewSchedule().size())), expectedModel);
    }

    @Test
    public void execute_listIsEmpty_showsNoInterviewsListed() {
        model = new ModelManager(new AddressBook(), new InterviewSchedule(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getInterviewSchedule(), new UserPrefs());
        assertCommandSuccess(new ViewCommand(predicate), model, new CommandResult(String
                .format(Messages.MESSAGE_INTERVIEWS_LISTED_OVERVIEW, model.getFilteredInterviewSchedule().size())),
                expectedModel);
    }
}
