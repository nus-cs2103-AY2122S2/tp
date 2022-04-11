package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListMeetingCommandTest {

    private Model model;
    private Model expectedModel;

    @Test
    public void execute_emptyMeetingListShowUpcoming_showsSameList() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(new ListMeetingCommand(null, false),
                model,
                ListMeetingCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_emptyMeetingListShowAll_showsSameList() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(new ListMeetingCommand(null, true),
                model,
                ListMeetingCommand.MESSAGE_SUCCESS_ALL,
                expectedModel);
    }

}
