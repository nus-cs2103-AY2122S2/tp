package seedu.address.logic.commands.person;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getGenericAddressBookWithSortedStrengths;
import static seedu.address.testutil.TypicalPersons.getGenericAddressBookWithUnsortedStrengths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.TaskBook;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortStrengthCommand.
 */
public class SortStrengthCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getGenericAddressBookWithUnsortedStrengths(), new TaskBook(),
                new StrategyBoard(), new UserPrefs());
        expectedModel = new ModelManager(getGenericAddressBookWithSortedStrengths(),
                new TaskBook(), new StrategyBoard(), new UserPrefs());
    }

    @Test
    public void execute_unsortedList_success() {
        CommandTestUtil.assertCommandSuccess(new SortStrengthCommand(), model,
                SortStrengthCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyList_throwsCommandException() {
        Model emptyModel = new ModelManager(new AddressBook(), new TaskBook(),
                new StrategyBoard(), new UserPrefs());

        assertCommandFailure(new SortStrengthCommand(), emptyModel, Messages.MESSAGE_EMPTY_PERSON_LIST);
    }
}
